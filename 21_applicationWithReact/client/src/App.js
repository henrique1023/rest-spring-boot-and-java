import React from "react";
import Routes from "./Routes";
import { BrowserRouter } from "react-router-dom";
import './global.css'

export default function App() {
  return (
    <BrowserRouter>
      <React.Fragment>
        <Routes/>
      </React.Fragment>
    </BrowserRouter>
  );
} 