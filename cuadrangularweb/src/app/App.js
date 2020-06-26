import React, {Component} from 'react';
import Registro from './registro/Registro';
import Cuadrangular from './cuadrangular/Cuadrangular';
import Tabla from './tabla/Tabla'
import './App.css';

class App extends Component {
  render(){
    return (
      <div className="App">
        <header className="App-header">
          <h1>Cuadrangular OAS</h1>
        </header>
        <div className="container">
          <Registro />
          <Cuadrangular />
          <Tabla />
        </div>
      </div>
    );
  }
}

export default App;
