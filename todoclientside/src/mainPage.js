import ReactDOM from 'react-dom';
import  { Redirect, Route } from 'react-router-dom'
import React, { Component } from 'react';
import Moment from 'moment';
import ReactTable from "react-table";
import "react-table/react-table.css";
import './index.css';

class MainPage extends Component {
    constructor() {
        super();
        this.state = {
            data: [],
            newname: '',
            updatename: '',
            error: '',
            selected: null,
            redirect: false,
            logout: false
        };
        
        this.handlenewnamechange = this.handlenewnamechange.bind(this);
        this.handleupdatenamechange = this.handleupdatenamechange.bind(this);
        this.fetchList = this.fetchList.bind(this, 1000);
        this.handleAlternate = this.handleAlternate.bind(this);
        this.addList = this.addList.bind(this);
        this.handlerowselect = this.handlerowselect.bind(this);
        this.deleteList = this.deleteList.bind(this);
        this.handleupdate = this.handleupdate.bind(this);
        this.handledelete = this.handledelete.bind(this);
        this.updateList = this.updateList.bind(this);
        this.dismissError = this.dismissError.bind(this);
        this.setErrorIfNotSuccess = this.setErrorIfNotSuccess.bind(this);
        this.handlelogout = this.handlelogout.bind(this);
    }
    componentDidMount() {
        if(localStorage.getItem('userId'))    
        this.fetchList();
    }
    
    fetchList = async () =>{
            await fetch(localStorage.getItem('api') + "getTodolistsByOwner?userId=" + localStorage.getItem('userId'), {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                }
        }).then(res => res.json())
            .then(json =>  this.setState({data: json}));
    }
    
    addList = async () =>{
        await fetch(localStorage.getItem('api') + "addTodolist?name=" + this.state.newname + "&userId=" + localStorage.getItem('userId'), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json => this.setErrorIfNotSuccess(json));
    }
    
    updateList = async () =>{
        await fetch(localStorage.getItem('api') + "updateTodolist?name=" + this.state.updatename + "&id=" + this.state.selected, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json => this.setErrorIfNotSuccess(json));
    }
    
    deleteList = async()=>{
        await fetch(localStorage.getItem('api') + "deleteTodoList?id=" + this.state.selected, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json => this.setErrorIfNotSuccess(json));
    }
    
    handleAlternate(evt){
        evt.preventDefault();
        this.addList().then(() => this.fetchList());
        this.setState({newname : ''});
    }
    
    handleupdate(evt){
        evt.preventDefault();
        this.updateList().then(() => this.fetchList());
        this.setState({updatename : '',
                       selected : null});
    }
    
    handledelete(evt){
        evt.preventDefault();
        this.deleteList().then(() => this.fetchList());
    }
    
    handlenewnamechange(evt) {
        evt.preventDefault();
        this.setState({
            newname: evt.target.value
        });
    };
     
    handleupdatenamechange(evt) {
        evt.preventDefault();
        this.setState({
            updatename: evt.target.value
        });
    };
    
    handlerowselect = async (rowinfo) =>{
        await this.setState({
            selected: rowinfo.original.id,
            updatename: rowinfo.original.name 
        })
    }
    
    
    handledetail(evt){
        evt.preventDefault();  
        localStorage.setItem('listid', this.state.selected);
        this.setState({
          redirect: true
        });
    }

    dismissError(evt) {
        evt.preventDefault();  
        this.setState({ error: '' });
    }
    
    setErrorIfNotSuccess(json){
        if(json !== "success"){
            this.setState({error : json})
        }else{
            this.setState({
                newname: '',
                updatename: '',
                selected: null,
                error: ''})
            
        }
    }
    
    handlelogout(evt){
        evt.preventDefault();
        localStorage.setItem("userId", 0)
        this.setState({logout : true})
    }
    
    render() {
        
    if (this.state.logout === false){
        if (this.state.redirect === false) {
            return (

                    <div className="html">

                        <form onSubmit={this.handleSubmit} className="main">  

                            <div>
                              <input className="addtodolistarea"  data-test="newname" value={this.state.newname} onChange={this.handlenewnamechange} />
                            <button className="addtodolistbutton" onClick={this.handleAlternate}>Add TodoList</button>
                            <button className="logoutButton" onClick={this.handlelogout}>Logout</button>
                            </div>

                            {
                              this.state.error &&
                              <h3 data-test="error" onClick={this.dismissError}>
                                <button className="xButton" onClick={this.dismissError}>✖</button>
                                {this.state.error}
                              </h3>
                            }
                            <ReactTable
                                getTrProps={(state, rowInfo) => {
                                    if (rowInfo && rowInfo.row) {
                                      return {
                                        onClick: (e) => {
                                          this.handlerowselect(rowInfo);
                                        },
                                        style: {
                                          background: rowInfo.original.id === this.state.selected ? '#00afec' : 'white',
                                          color: rowInfo.original.id === this.state.selected ? 'white' : 'black'
                                        }
                                      }
                                    }else{
                                      return {}
                                    }
                                  }}
                                data={this.state.data}
                                columns={[
                                      {
                                        Header: "Name",
                                        accessor: "name",
                                      },
                                      {
                                        id: "crtDate",
                                        Header: "Create Date",
                                        accessor: d =>{
                                            return Moment(d.crtDate)
                                              .local()
                                              .format("DD/MM/YYYY")
                                          },
                                        filterable: false
                                      }
                                      ,]}
                                filterable 
                                selectable
                                defaultPageSize={10}
                                showPageSizeOptions = {false}
                                className="-striped -highlight whiteBg"
                              />
                            {
                            this.state.selected && 
                                <div>
                                    <button className="deletetodolistbutton" onClick={this.handledelete.bind(this)}>Delete</button>
                                    <button className="detailtodolistbutton" onClick={this.handledetail.bind(this)}>Show Todo Items</button>
                                </div>   
                            }
                            {
                                this.state.selected &&
                                <div className="updateTodoDiv">
                                    <div>
                                        <label className="updateTodoNameLabel" >Name</label>
                                        <input className="addtodolistarea"  data-test="updatename" value={this.state.updatename} onChange={this.handleupdatenamechange} />
                                    </div>
                                    <button className="updatetodolistbutton" onClick={this.handleupdate}>Update Todo List</button>
                                </div>
                            }
                        </form>
                    </div>
                    )
            }else if(this.state.redirect === true){
                return (
                      <Route
                          render={props =>(
                              <Redirect
                                to={{
                                  pathname: "/details",
                                  state: { from: props.location }
                                }}
                              />
                            )
                          }
                        />
                  );
            }
        }else if(this.state.logout === true){
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
export default MainPage;
ReactDOM.render(
        <MainPage />,
        document.getElementById('root')

        );
