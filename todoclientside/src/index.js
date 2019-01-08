import React from 'react'
import { render } from 'react-dom'
import { BrowserRouter } from 'react-router-dom'
import App from './App';


localStorage.setItem('api', "http://127.0.0.1:8080/todoServiceSide/");
render((
  <BrowserRouter>
    <App />
  </BrowserRouter>
), document.getElementById('root'));
