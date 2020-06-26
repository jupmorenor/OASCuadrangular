import React, {Component} from 'react';
import './registro.css'

class Registro extends Component {
  constructor(props) {
    super(props);
    this.registrarEquipo = this.registrarEquipo.bind(this);
    this.state = {
      estado: "",
    }
  }

  componentDidMount() {
    var boton = document.getElementById('registrar');
    boton.addEventListener('click', this.registrarEquipo)
  }
  
  async registrarEquipo() {
    var equipo = document.getElementById('nombre-equipo');
    var data = '?nombreq='+equipo.value;
    await fetch('http://localhost:8080/equipo/registrar'+ data, {
      method: 'post',
      headers: { 
        Accept: 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }).then(response => {
      return response.text();
    }).then(text => {
      this.setState({estado: text});
    }).catch(error => {
      alert(error);
    });
    
  }
  
  render() {
    return (
      <div className='componentContainer'>
        <h3>Registrar equipo</h3>
        <input type='text' id='nombre-equipo'/>
        <button id='registrar'>Enviar</button>
        <p className="estado">{this.state.estado}</p>
      </div>
    )
  }
}

export default Registro;
