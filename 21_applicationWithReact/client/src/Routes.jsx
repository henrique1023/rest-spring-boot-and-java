import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/login/Index";

export default props => (
    <Routes>
        <Route exact path='/' element={<Login/>}/>
    </Routes>
)
   