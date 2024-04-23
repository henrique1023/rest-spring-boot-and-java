import React, {useState, useEffect} from "react";
import { Link, useNavigate, useParams } from "react-router-dom";
import { FiArrowLeft } from "react-icons/fi";

import './styles.css';
import logoImage from '../../assets/logo.svg'
import api from "../../services/api";

export default function NewBook(){

    const [id, setId] = useState(null)
    const [author, setAuthor] = useState('')
    const [launchDate, setLaunchDate] = useState('')
    const [price, setPrice] = useState('')
    const [title, setTitle] = useState('')

    const navigate = useNavigate();

    const {bookId} = useParams();

    const accessToken = localStorage.getItem('accessToken')
    const refleshToken = localStorage.getItem('refleshToken')

    useEffect(() => {
        if (bookId !== '0') {
           loadBook()
        } 
        
    }, [bookId])

    async function loadBook(){
        try {
            const resp = await api.get(`api/book/v1/${bookId}`, { 
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }})
            
                let adjustedDate = resp.data.launchDate.split('T',10)[0];

                setId(resp.data.id)
                setAuthor(resp.data.author)
                setTitle(resp.data.title)
                setLaunchDate(adjustedDate)
                setPrice(resp.data.price)
        } catch (error) {
            alert('Error load book')
            navigate('/')
        }
    }

    async function saveOrUpdate(e){
        e.preventDefault();

        const data = {
            title,
            author,
            launchDate,
            price,
        }

        try {
            if(bookId === '0'){
                 await api.post('api/book/v1', data, { 
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
                })
            }else {
                data.id = id
                await api.post('api/book/v1', data, { 
                    headers: {
                        Authorization: `Bearer ${accessToken}`
                }
                })
            }
           
            navigate('/books')
        } catch (error) {
            alert('Error while recording!')
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Erudio" />
                    <h1>{bookId === '0' ? 'Add' : 'Edit'} new book</h1>
                    <p>Enter the book information and click on '{bookId === '0' ? 'Add' : 'Edit'}'</p>
                    <Link className="back-link" to={"/books"}>
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Home
                    </Link>
                </section>
                <form onSubmit={saveOrUpdate}>
                    <input type="text" placeholder="Title" 
                    value={title} onChange={e => setTitle(e.target.value)}/>
                    <input type="text" placeholder="Author" 
                    value={author} onChange={e => setAuthor(e.target.value)}/>
                    <input type="date"
                    value={launchDate} onChange={e => setLaunchDate(e.target.value)} />
                    <input type="text" placeholder="price"
                    value={price} onChange={e => setPrice(e.target.value)} />

                    <button className="button" type="submit">{bookId === '0' ? 'Add' : 'Edit'}</button>
                </form>
            </div>
        </div>
    )
}