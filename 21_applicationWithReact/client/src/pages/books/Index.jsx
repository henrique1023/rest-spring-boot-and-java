import React, {useState, useEffect} from "react";
import { Link, useNavigate  } from "react-router-dom";
import { FiPower, FiEdit, FiTrash2 } from "react-icons/fi";

import './Styles.css'
import logoImage from '../../assets/logo.svg'
import api from "../../services/api";

export default function Books(){
    const [books, setBooks] = useState([])
    const [page, setPage] = useState(1)

    const navigate = useNavigate();

    const accessToken = localStorage.getItem('accessToken')
    const username = localStorage.getItem('username') !== null ? localStorage.getItem('username') : ''

    function verifyToken(){
        if(accessToken === undefined || accessToken === '' || accessToken === null){
            return true
        }else{
            return false
        }
    }

    async function editBook(id){
        try {
            navigate(`/book/new/${id}`)
        } catch (error) {
            alert('Erro ao editar livro!')
        }
        
    }

    async function deleteBook(id){
        try {
            await api.delete(`api/book/v1/${id}`, { headers: {
                Authorization: `Bearer ${accessToken}`
            }})

            setBooks(books.filter(book => book.id !== id))
        } catch (error) {
            alert('Erro ao deletar livro!')
        }
       
    }

    async function logout(){
        localStorage.clear();
        navigate('/')
    }

    async function fetchMoreBooks(){
        try {
            const response = await api.get('api/book/v1', { 
            headers: {
                Authorization: `Bearer ${accessToken}`
            },
            params: {
                page: page,
                size: 4,
                direction: 'asc'
            }
            })

            setBooks([...books, ...response.data._embedded.bookVOList])
            setPage(page + 1)
        } catch (error) {
            
        }
        
    }

    useEffect(() => {
        console.log(accessToken)
        if(verifyToken()){
            navigate('/')
        }else{
            fetchMoreBooks()
        }
        
    }, []); 
    
    return (
       <div className="book-container">
            <header>
                <img src={logoImage} alt="Erudio" />
                <span>Welcome, <strong>{username.toUpperCase}</strong>!</span>
                <Link className="button" to="/book/new/0">Add new book</Link>
                <button  onClick={() => logout()}  type="button">
                    <FiPower size={18} color="#251fc5"/>
                </button>
            </header>
            <h1>Registered Books</h1>
            <ul>
                {books.map(book => (
                    <li key={book.id}>
                        <strong>Title:</strong>
                        <p>{book.title}</p>
                        <strong>Author:</strong>
                        <p>{book.author}</p>
                        <strong>Price:</strong>
                        <p>{Intl.NumberFormat('pt-BR', {style:'currency', currency:'BRL'}).format(book.price)}</p>
                        <strong>Release Data:</strong>
                        <p>{Intl.DateTimeFormat('pt-BR').format(new Date(book.launchDate))}</p>

                        <button onClick={() => editBook(book.id)} ype="button">
                            <FiEdit size={20} color="#251fc5"/>
                        </button>
                        <button onClick={() => deleteBook(book.id)} type="button">
                            <FiTrash2 size={20} color="#251fc5"/>
                        </button>
                    </li>
                ))}
                
            </ul>

            <button className="button" onClick={() => fetchMoreBooks()}>Carregar mais</button>
       </div>
    )
}