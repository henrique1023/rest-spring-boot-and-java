import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/login/Index";
import Books from "./pages/books/Index";
import NewBook from "./pages/newBook";

export default props => (
    <Routes>
        <Route exact path='/' element={<Login/>}/>
        <Route path='/books' element={<Books/>}/>
        <Route path='/book/new' element={<NewBook/>}/>
    </Routes>
)
   