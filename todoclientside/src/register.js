import ReactDOM from 'react-dom';
import  {Redirect,Route } from 'react-router-dom'
import React, { Component } from 'react';
import './index.css';

class RegisterPage extends Component {
    constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      confirmPassword: '',
      error: '',
      redirect: false
    };
    
    this.handlePassChange = this.handlePassChange.bind(this);
    this.handleUserChange = this.handleUserChange.bind(this);
    this.handleConfirmPassChange = this.handleConfirmPassChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.dismissError = this.dismissError.bind(this);
}

  dismissError() {
    this.setState({ error: '' });
  }
  
  handleSubmit(evt) {
    evt.preventDefault();
        this.call = localStorage.getItem('api') + 'register?';
        if(this.state.username){
            this.call = this.call + 'name=' + this.state.username + '&';
        }
        if(this.state.password){
            this.call = this.call + 'password=' +  this.state.password + '&';
        }
        if(this.state.confirmPassword){
            this.call = this.call + 'confPassword=' +  this.state.confirmPassword;
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
        if(json === 'success'){
            this.setState({
              redirect: true
            });
        }else{
            this.setState({
              redirect: false
            });
            return this.setState({ error: json});
        }
    }
    
    handleAlternate(evt){
        evt.preventDefault();      
        this.setState({
          redirect: true
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

  handleConfirmPassChange(evt) {
    this.setState({
      confirmPassword: evt.target.value
    });
  }

  render() {
    if (this.state.redirect === false) {
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
              <input className="registernamearea" type="text" data-test="username" value={this.state.username} onChange={this.handleUserChange} />
            </div>
            <div>
              <label className="LoginLabel" >Password</label>
              <input className="registerpasswordarea" type="password" data-test="password" value={this.state.password} onChange={this.handlePassChange} />
            </div>
            <div>
              <label className="LoginLabel" >Confirm Password</label>
              <input className="passwordarea" type="password" data-test="password" value={this.state.confirmPassword} onChange={this.handleConfirmPassChange} />
            </div>
            
            <button className="registerbutton" onClick={this.handleAlternate.bind(this)}>Back</button>
            <input className="registerbutton" type="submit" value="Sign Up" data-test="submit"/>
            </form>
            </div>
        );
    }else if(this.state.redirect === true){
      return (
            <Route
                render={props =>(
                    <Redirect
                      to={{
                        pathname: "/",
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

export default RegisterPage;
ReactDOM.render(
  <RegisterPage />,
  document.getElementById('root')

);
