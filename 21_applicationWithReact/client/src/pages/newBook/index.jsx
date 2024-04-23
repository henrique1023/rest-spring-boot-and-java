import React, {useState} from "react";
import { Link, useNavigate } from "react-router-dom";
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

    const accessToken = localStorage.getItem('accessToken')
    const refleshToken = localStorage.getItem('refleshToken')

    async function createNewBook(e){
        e.preventDefault();

        const data = {
            title,
            author,
            launchDate,
            price,
        }

        try {
            await api.post('api/book/v1', data, { 
                headers: {
                    Authorization: `Bearer ${accessToken}`
                }
            })
            navigate('/books')
        } catch (error) {
            alert('Error while recording!' + error)
        }
    }

    return (
        <div className="new-book-container">
            <div className="content">
                <section className="form">
                    <img src={logoImage} alt="Erudio" />
                    <h1>Add new book</h1>
                    <p>Enter the book information and click on 'Add'</p>
                    <Link className="back-link" to={"/books"}>
                        <FiArrowLeft size={16} color="#251fc5"/>
                        Home
                    </Link>
                </section>
                <form onSubmit={createNewBook}>
                    <input type="text" placeholder="Title" 
                    value={title} onChange={e => setTitle(e.target.value)}/>
                    <input type="text" placeholder="Author" 
                    value={author} onChange={e => setAuthor(e.target.value)}/>
                    <input type="date"
                    value={launchDate} onChange={e => setLaunchDate(e.target.value)} />
                    <input type="text" placeholder="price"
                    value={price} onChange={e => setPrice(e.target.value)} />

                    <button className="button" type="submit">Add</button>
                </form>
            </div>
        </div>
    )
}