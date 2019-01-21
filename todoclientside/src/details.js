import ReactDOM from 'react-dom';
import  {Redirect, Route } from 'react-router-dom'
import React, { Component } from 'react';
import ReactTable from "react-table";
import Moment from 'moment';
import "react-table/react-table.css";
import 'react-day-picker/lib/style.css';
import './index.css';

class Detail extends Component {
    constructor() {
        super();
        this.state = {
            data: [],
            newname: '',
            newdetail: '',
            newduedate: '',
            newdependantname: '',
            updatename: '',
            updatedetail: '',
            updatedueDate: '',
            updatedependantname: '',
            error: '',
            selected: null,
            redirect: false
        };
        
        this.handlenewnamechange = this.handlenewnamechange.bind(this);
        this.handlenewdetailchange = this.handlenewdetailchange.bind(this);
        this.handlenewduedatechange = this.handlenewduedatechange.bind(this);
        this.handlenewdependantnamechange = this.handlenewdependantnamechange.bind(this);
        this.handleupdatenamechange = this.handleupdatenamechange.bind(this);
        this.handleupdatedetailchange = this.handleupdatedetailchange.bind(this);
        this.handleupdateduedatechange = this.handleupdateduedatechange.bind(this);
        this.handleupdatedependantnamechange = this.handleupdatedependantnamechange.bind(this);
        this.fetchList = this.fetchList.bind(this, 1000);
        this.handleAlternate = this.handleAlternate.bind(this);
        this.add = this.add.bind(this);
        this.handlerowselect = this.handlerowselect.bind(this);
        this.delete = this.delete.bind(this);
        this.handleupdate = this.handleupdate.bind(this);
        this.handledelete = this.handledelete.bind(this);
        this.handlecomplete = this.handlecomplete.bind(this);
        this.update = this.update.bind(this);
        this.dismissError = this.dismissError.bind(this);
        this.setErrorIfNotSuccess = this.setErrorIfNotSuccess.bind(this);
        if(localStorage.getItem('listid'))
        this.fetchList();
    }
    componentDidMount() {        
    }
    
    fetchList = async () =>{
        await fetch(localStorage.getItem('api') + "getTodosByTodolistService?listId=" + localStorage.getItem('listid'), {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json =>  this.setState({data: json}));
    }
    
    add = async () =>{
        await fetch(localStorage.getItem('api') + "addTodoItemService?listid=" 
                + localStorage.getItem('listid') 
                + "&name=" 
                + this.state.newname
                + "&detail=" 
                + this.state.newdetail
                + "&dueDate=" 
                + this.state.newduedate
                + "&dependantTodoId=" 
                + this.state.newdependantname
                , {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json => this.setErrorIfNotSuccess(json));
    }
    
    update = async () =>{
        await fetch(localStorage.getItem('api') + "updateTodoItem?id=" 
                + this.state.selected
                + "&name=" 
                + this.state.updatename
                + "&detail=" 
                + this.state.updatedetail
                + "&dueDate=" 
                + this.state.updateduedate
                + "&dependantTodoId=" 
                + this.state.updatedependantname
                , {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json => this.setErrorIfNotSuccess(json));
    }
    
    delete = async()=>{
        await fetch(localStorage.getItem('api') + "deleteTodoService?id=" + this.state.selected, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(json => this.setErrorIfNotSuccess(json));
    }
    
    complete = async()=>{
        await fetch(localStorage.getItem('api') + "completeTodo?id=" + this.state.selected, {
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
        this.add().then(() => this.fetchList());
    }
    
    handleupdate(evt){
        evt.preventDefault();
        this.update().then(() => this.fetchList());
    }
    
    handledelete(evt){
        evt.preventDefault();
        this.delete().then(() => this.fetchList());
    }
    
    handlecomplete(evt){
        evt.preventDefault();
        this.complete().then(() => this.fetchList());
    }
    
    handlenewnamechange(evt) {
        evt.preventDefault();
        this.setState({
            newname: evt.target.value
        });
    };
    
    handlenewdetailchange(evt) {
        evt.preventDefault();
        this.setState({
            newdetail: evt.target.value
        });
    };
    
    handlenewduedatechange(evt) {
        evt.preventDefault();
        this.setState({ newduedate:  evt.target.value });
    }
    
    handlenewdependantnamechange(evt) {
        evt.preventDefault();
        this.setState({
            newdependantname: evt.target.value
        });
    };
    
    handleupdatenamechange(evt) {
        evt.preventDefault();
        this.setState({
            updatename: evt.target.value
        });
    };
    
    handleupdatedetailchange(evt) {
        evt.preventDefault();
        this.setState({
            updatedetail: evt.target.value
        });
    };
    
    handleupdateduedatechange(evt) {
        evt.preventDefault();
        this.setState({ updateduedate:  evt.target.value });
    }
    
    handleupdatedependantnamechange(evt) {
        evt.preventDefault();
        this.setState({
            updatedependantname: evt.target.value
        });
    };
     
    handlerowselect = async (rowinfo) =>{
        await this.setState({
            selected: rowinfo.original.id,
            updatename: rowinfo.original.name,
            updatedetail: rowinfo.original.detail,
            updateduedate: rowinfo.row.dueDate
        });
        if(rowinfo.row.dependedTodoId){
            this.setState({
            updatedependantname: rowinfo.row.dependedTodoId
        });
        }else{
            this.setState({
                updatedependantname: ""
            });
        }
    }
    
    
    handleBack(evt){
        evt.preventDefault();  
        localStorage.setItem("listid", 0)
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
            this.setState({selected: null,
                newname : '',
                newdetail: '',
                newduedate:'',
                newdependantname: '',
                updatename: '',
                updatedetail: '',
                updateduedate: '',
                updatedependantname: '',
                error: ''})
            
        }
    }
    
    render() {
    if (this.state.redirect === false) {
        return (
                
                <div className="htmlDetail">
        
                    <form onSubmit={this.handleSubmit} className="main">  
                        <div>
                            <button className="deletetodolistbutton" onClick={this.handleBack.bind(this)}>Back</button>
                        </div>
                        <div className="updateTodoDiv">
                        <div>
                            <label className="updateTodoItemNameLabel" >Name</label>
                            <input className="addtodolistarea"  data-test="newname" value={this.state.newname} onChange={this.handlenewnamechange} />
                        </div>
                        <div>
                            <label className="updateTodoItemDetailLabel" >Detail</label>
                            <textarea rows="5" className="detail" data-test="newdetail" value={this.state.newdetail} onChange={this.handlenewdetailchange} />
                        </div>
                        <div>
                            <label className="updateTodoItemDueDateLabel" >Due Date</label>
                            <input className="addtodolistarea"  data-test="newduedate" value={this.state.newduedate} onChange={this.handlenewduedatechange} />
                                  
                        </div>
                        <div>
                            <label className="updateTodoNameLabel" >Dependant Todo Name</label>
                            <input className="addtodolistarea"  data-test="newdependantname" value={this.state.newdependantname} onChange={this.handlenewdependantnamechange} />
                        </div>
                            <button className="addtodobutton" onClick={this.handleAlternate}>Add TodoList</button>

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
                                    Header: "Detail",
                                    accessor: "detail"
                                  },
                                  {
                                    Header: "Status",
                                    accessor: "status"
                                  },
                                  {
                                    id: "crtdate",
                                    Header: "Created Date",
                                    accessor: d =>{
                                        return Moment(d.crtDate)
                                          .local()
                                          .format("DD/MM/YYYY")
                                      },
                                    filterable: false
                                  },
                                  {
                                    id: "dueDate",
                                    Header: "Due Date",
                                    accessor: d =>{
                                        return Moment(d.dueDate)
                                          .local()
                                          .format("DD/MM/YYYY")
                                      },
                                    filterable: false
                                  },
                                  {
                                    Header: "Depended Todo Name",
                                    accessor: "dependedTodoId",
                                  }
                                  ,]}
                            filterable 
                            selectable
                            defaultPageSize={5}
                            showPageSizeOptions = {false}
                            className="-striped -highlight whiteBg"
                          />
                        {
                        this.state.selected && 
                            <div>
                                <button className="deletetodolistbutton" onClick={this.handledelete.bind(this)}>Delete</button>
                                <button className="detailtodolistbutton" onClick={this.handlecomplete.bind(this)}>Complete Task</button>
                            </div>   
                        }
                        {
                            this.state.selected &&
                                <div className="updateTodoDiv">
                                <div>
                                    <label className="updateTodoItemNameLabel" >Name</label>
                                    <input className="addtodolistarea"  data-test="updatename" value={this.state.updatename} onChange={this.handleupdatenamechange} />
                                </div>
                                <div>
                                                
                                    <div>
                                        <label className="updateTodoItemDetailLabel" >Detail</label>
                                    </div>
                                        <textarea rows="5" className="detail"  data-test="updatedetail" value={this.state.updatedetail} onChange={this.handleupdatedetailchange} />
                                </div>
                                <div>
                                    <label className="updateTodoItemDueDateLabel" >Due Date</label>
                                    <input className="addtodolistarea"  data-test="updateduedate" value={this.state.updateduedate} onChange={this.handleupdateduedatechange} />
                                </div>
                                <div>
                                    <label className="updateTodoNameLabel" >Dependant Todo Name</label>
                                    <input className="addtodolistarea"  data-test="updatedependantname" value={this.state.updatedependantname} onChange={this.handleupdatedependantnamechange} />
                                </div>
                                    <button className="addtodobutton" onClick={this.handleupdate}>Update Todo Item</button>
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
                              pathname: "/mainpage",
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
export default Detail;
ReactDOM.render(
        <Detail />,
        document.getElementById('root')

        );