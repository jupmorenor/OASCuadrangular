import React, {Component} from 'react';
import './cuadrangular.css'

class Cuadrangular extends Component {
  constructor(props) {
    super(props);
    this.obtenerDatos = this.obtenerDatos.bind(this);
    this.state= {
      data: []
    }
    this.campos = ['Goles', 'Equipo 1', 'Equipo 2', 'Goles', '']
  }

  obtenerDatos() {
    fetch('http://localhost:8080/equipo/cargar')
    .then(response => {
      return response.json()
    }).then(json => {
      this.setState({data: json})
    })
  }

  componentDidMount() {
    var boton = document.getElementById('generar');
    boton.addEventListener('click', this.obtenerDatos);
  }

  registrarPartido(event) {
    event.preventDefault();
    var data = '?';
    data += event.target.equipo1.name+'='+event.target.equipo1.value; 
    data += '&'+event.target.equipo2.name+'='+event.target.equipo2.value; 
    data += '&'+event.target.goles1.name+'='+event.target.goles1.value; 
    data += '&'+event.target.goles2.name+'='+event.target.goles2.value;
    alert(data)
    fetch('http://localhost:8080/partido/registrar'+data, {
      method: 'post',
      headers: { 
        Accept: 'application/json',
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin': '*'
      }
    }).then(response => {
      return response.text();
    }).then(text => {
      alert(text);
    })

  }

  render() {
    return (
      <div className='componentContainer'>
        <h3>Marcadores</h3>
        <table className="cuadrangular">
          <thead>
            <tr><td className='celda'>
            {
              this.campos.map((campo, index) => {
                return <p key={index}>{campo}</p>
              })
            }
            </td></tr>
          </thead>
          <tbody>
            {
              this.state.data.map((equipo1, index1) => {
                return this.state.data.map((equipo2, index2) => {
                  if (equipo1.id < equipo2.id) {
                    return (
                      <tr className='partido' key={2*index1+index2}>
                        <td className='celda'>
                          <form method='post' action='' onSubmit={this.registrarPartido} className='celda'>
                            <input className='input' type='text' name='goles1' />
                            <input type='hidden' value={equipo1.nombre} name='equipo1' />
                            <p>{equipo1.nombre}</p>
                            <p>{equipo2.nombre}</p>
                            <input type='hidden' value={equipo2.nombre} name='equipo2' />
                            <input className='input' type='text' name='goles2' />
                            <button name='actualizar'>Actualizar</button>
                          </form>
                        </td>
                      </tr>
                    )
                  } else {
                    return <></>
                  }
                })
              })
            }
          </tbody>
        </table>
        <button id='generar'>Generar partidos</button>
      </div>
    )
  }
}

export default Cuadrangular;
