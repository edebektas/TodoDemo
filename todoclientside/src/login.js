import ReactDOM from 'react-dom';
import  {Redirect,Route } from 'react-router-dom'
import React, { Component } from 'react';
import './index.css';

class LoginPage extends Component {
    constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: '',
      redirect: 'here'
    };
    

    
    this.handlePassChange = this.handlePassChange.bind(this);
    this.handleUserChange = this.handleUserChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.dismissError = this.dismissError.bind(this);
    this.redirectIfSuccess = this.redirectIfSuccess.bind(this);
    
}

  dismissError() {
    this.setState({ error: '' });
  }
  
  handleSubmit(evt) {
    evt.preventDefault();
        this.call = localStorage.getItem('api') + 'login?';
        if(this.state.username){
            this.call = this.call + 'name=' + this.state.username + '&';
        }
        if(this.state.password){
            this.call = this.call + 'password=' +  this.state.password;
        }
        fetch(this.call,  {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            }
          }).then(res => res.json())
            .then(json => this.redirectIfSuccess(json));
  }

    redirectIfSuccess(json){
        if(json.indexOf('Error') === -1){
            localStorage.setItem('userId', json);
            this.setState({
              redirect: 'main'
            });
        }else{
            this.setState({
              redirect: 'here'
            });
            return this.setState({ error: json});
        }
    }
    
    handleAlternate(evt){
        evt.preventDefault(); 
        evt.stopPropagation();     
        this.setState({
          redirect: 'register'
        });
    }
    
  handleUserChange(evt) {
    this.setState({
      username: evt.target.value
    });
  };

  handlePassChange(evt) {
    this.setState({
      password: evt.target.value
    });
  }

  render() {
    if (this.state.redirect === 'here') {
        return (
            <div className="html">

            <form onSubmit={this.handleSubmit} className="Login">
              {
                this.state.error &&
                <h3 data-test="error" onClick={this.dismissError}>
                  <button className="xButton" onClick={this.dismissError}>✖</button>
                  {this.state.error}
                </h3>
              }
            <div>
              <label className="LoginLabel" >User Name</label>
              <input className="namearea" type="text" data-test="username" value={this.state.username} onChange={this.handleUserChange} />
            </div>
            <div>
              <label className="LoginLabel" >Password</label>
              <input className="passwordarea" type="password" data-test="password" value={this.state.password} onChange={this.handlePassChange} />
            </div>
            
            <div>
            <input className="loginbutton" type="submit" value="Log In" data-test="submit"/>
            </div>
            <div>
            <button className="signupbutton" onClick={this.handleAlternate.bind(this)} onKeyDown={(e)=>{e.target.keyCode === 13 && e.preventDefault();}}>Sign Up</button>
            </div>
            </form>
            </div>
        );
    }else if(this.state.redirect === 'main'){
          return (
                  
                <Route
                    render={props =>(
                            
                        <Redirect
                          to={{
                            pathname: "/mainPage",
                            state: { from: props.location }
                          }}
                        />
                      )
                    }
                  />
                  
            );
    }else if(this.state.redirect === 'register'){
     return (
            <Route
                render={props =>(
                    <Redirect
                      to={{
                        pathname: "/register",
                        state: { from: props.location }
                      }}
                    />
                  )
                }
              />
        );
    }
  }
}

export default LoginPage;
ReactDOM.render(
  <LoginPage />,
  document.getElementById('root')

);
