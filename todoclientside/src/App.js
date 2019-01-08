import React from 'react'
import {BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import mainpage from './mainPage'
import details from './details'
import login from './login'
import register from './register'

// The Main component renders one of the three provided
// Routes (provided that one matches). Both the /roster
// and /schedule routes will match any pathname that starts
// with /roster or /schedule. The / route will only match
// when the pathname is exactly the string "/"
const App = () => (
  <div>
    <Router>
        <Switch>
          <Route exact path='/details' component={details}/>
          <Route exact path='/mainpage' component={mainpage}/>
          <Route exact path='/register' component={register}/>
          <Route exact path='/' component={login}/>
        </Switch>
    </Router>
  </div>
);

export default App