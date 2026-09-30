import Icon from '../shared/Icon';
// ====================================
// COMPONENTE LISTA DE NOTIFICACIONES - VERSIÓN CON EDITAR
// ====================================

import React, { useState, useEffect } from 'react';
import { notificationService } from '../../services/notificationService';
import EditarNotificacion from './EditarNotificacion';
import './ListaNotificaciones_EduSoft.css';


function ListaNotificaciones() {
  
  // ── Rol del usuario autenticado ──────────────────────────────
  const usuario    = JSON.parse(localStorage.getItem('usuario'));
  const esProfesor = usuario?.rol === 'Profesor';
  // ────────────────────────────────────────────────────────────

  // ========== ESTADOS DEL COMPONENTE ==========
  
  const [notificaciones, setNotificaciones] = useState([]);
  const [notificacionesFiltradas, setNotificacionesFiltradas] = useState([]);
  const [textoBusqueda, setTextoBusqueda] = useState('');
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState('');
  const [notificacionSeleccionada, setNotificacionSeleccionada] = useState(null);
  
  //  NUEVO: Estado para controlar si estamos editando
  const [editando, setEditando] = useState(false);
  const [idEditar, setIdEditar] = useState(null);

  // ========== useEffect ==========
  
  useEffect(() => {
    cargarNotificaciones();
  }, []);

  // ========== FUNCIONES ==========
  
  const cargarNotificaciones = async () => {
    setCargando(true);
    setError('');
    
    try {
      const datos = await notificationService.listarTodas();
      setNotificaciones(datos);
      setNotificacionesFiltradas(datos);
      console.log('Notificaciones cargadas:', datos);
    } catch (error) {
      console.error('Error al cargar:', error);
      setError('Error al cargar las notificaciones. Verifica que el backend esté corriendo.');
    } finally {
      setCargando(false);
    }
  };

  const buscarNotificaciones = (e) => {
    const texto = e.target.value;
    setTextoBusqueda(texto);
    
    if (texto.trim() === '') {
      setNotificacionesFiltradas(notificaciones);
      return;
    }
    
    const filtradas = notificaciones.filter(notif => {
      const textoMin = texto.toLowerCase();
      const coincideId = notif.id && notif.id.toString().includes(texto);
      const coincideAsunto = notif.asunto && notif.asunto.toLowerCase().includes(textoMin);
      const coincideRemitente = notif.emailRemitente && notif.emailRemitente.toLowerCase().includes(textoMin);
      const coincideDestinatario = notif.emailDestinatario && notif.emailDestinatario.toLowerCase().includes(textoMin);
      
      return coincideId || coincideAsunto || coincideRemitente || coincideDestinatario;
    });
    
    setNotificacionesFiltradas(filtradas);
  };

  const verDetalles = (notificacion) => {
    setNotificacionSeleccionada(notificacion);
  };

  const cerrarDetalles = () => {
    setNotificacionSeleccionada(null);
  };

  //  NUEVO: Función para abrir el editor
  const abrirEditar = (id) => {
    setIdEditar(id);
    setEditando(true);
  };

  //  NUEVO: Función para cerrar el editor
  const cerrarEditar = () => {
    setEditando(false);
    setIdEditar(null);
  };

  //  NUEVO: Función que se ejecuta después de guardar
  const handleGuardado = () => {
    cerrarEditar();
    cargarNotificaciones();  // Recargamos la lista para ver los cambios
  };

  const formatearFecha = (fecha) => {
    if (!fecha) return 'No especificada';
    if (fecha.includes('-')) {
      const [año, mes, dia] = fecha.split('-');
      return `${dia}/${mes}/${año}`;
    }
    return fecha;
  };

  const obtenerColorPrioridad = (prioridad) => {
    switch(prioridad) {
      case 'Crítica':
        return 'critica';
      case 'Alta':
        return 'alta';
      case 'Media':
        return 'media';
      case 'Baja':
        return 'baja';
      default:
        return 'media';
    }
  };

  // ========== SI ESTAMOS EDITANDO, MOSTRAR EL EDITOR ==========
  
  if (editando) {
    return (
      <EditarNotificacion 
        notificacionId={idEditar}
        onCancelar={cerrarEditar}
        onGuardado={handleGuardado}
      />
    );
  }

  // ========== RENDER DE LA LISTA ==========
  
  return (
    <div className="lista-container">
      <h2><Icon name="clipboard" size={18} /> Lista de Notificaciones</h2>
      
      <div className="controles">
        <div className="buscador">
          <input
            type="text"
            placeholder=" Buscar por ID, asunto, email..."
            value={textoBusqueda}
            onChange={buscarNotificaciones}
            className="input-busqueda"
          />
          {textoBusqueda && (
            <button 
              onClick={() => {
                setTextoBusqueda('');
                setNotificacionesFiltradas(notificaciones);
              }}
              className="btn-limpiar-busqueda"
            >
              ✕
            </button>
          )}
        </div>
        
        <button 
          onClick={cargarNotificaciones}
          className="btn-recargar"
          disabled={cargando}
        >
           Recargar
        </button>
      </div>

      {error && (
        <div className="mensaje-error">
           {error}
        </div>
      )}

      {cargando && (
        <div className="cargando">
          <div className="spinner"></div>
          <p>Cargando notificaciones...</p>
        </div>
      )}

      {!cargando && !error && (
        <div className="contador">
          {textoBusqueda ? (
            <p>Se encontraron <strong>{notificacionesFiltradas.length}</strong> resultado(s)</p>
          ) : (
            <p>Total de notificaciones: <strong>{notificaciones.length}</strong></p>
          )}
        </div>
      )}

      {!cargando && !error && notificacionesFiltradas.length === 0 && (
        <div className="sin-resultados">
          <p><Icon name="mail" size={18} /> No se encontraron notificaciones</p>
          {textoBusqueda && <p>Intenta con otro término de búsqueda</p>}
        </div>
      )}

      {!cargando && !error && notificacionesFiltradas.length > 0 && (
        <div className="tabla-container">
          <table className="tabla-notificaciones">
            <thead>
              <tr>
                <th>ID</th>
                <th>Asunto</th>
                <th>Remitente</th>
                <th>Destinatario</th>
                <th>Tipo</th>
                <th>Prioridad</th>
                <th>Fecha Creación</th>
                <th>Estado</th>
                {esProfesor && <th>Acciones</th>}
                {!esProfesor && <th>Detalle</th>}
              </tr>
            </thead>
            <tbody>
              {notificacionesFiltradas.map((notif) => (
                <tr key={notif.id}>
                  <td>{notif.id}</td>
                  <td className="asunto-cell">
                    {notif.asunto || 'Sin asunto'}
                  </td>
                  <td>{notif.emailRemitente || 'N/A'}</td>
                  <td>{notif.emailDestinatario || 'N/A'}</td>
                  <td>
                    <span className="badge tipo">
                      {notif.tipoNotificacion || 'N/A'}
                    </span>
                  </td>
                  <td>
                    <span className={`badge prioridad ${obtenerColorPrioridad(notif.prioridad)}`}>
                      {notif.prioridad || 'N/A'}
                    </span>
                  </td>
                  <td>{formatearFecha(notif.fechaCreacion)}</td>
                  <td>
                    {notif.estado ? (
                      <span className="estado activo"><Icon name="check" size={18} /> Activo</span>
                    ) : (
                      <span className="estado inactivo"><Icon name="close" size={18}/> Inactivo</span>
                    )}
                  </td>
                  <td>
                    <div className="acciones-grupo">
                      <button 
                        onClick={() => verDetalles(notif)}
                        className="btn-ver-detalles"
                        title="Ver detalles"
                      >
                        
                      </button>
                      {/* Solo Profesor puede editar */}
                      {esProfesor && (
                        <button 
                          onClick={() => abrirEditar(notif.id)}
                          className="btn-editar"
                          title="Editar"
                        >
                          
                        </button>
                      )}
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* Modal de detalles */}
      {notificacionSeleccionada && (
        <div className="modal-overlay" onClick={cerrarDetalles}>
          <div className="modal-content" onClick={(e) => e.stopPropagation()}>
            <div className="modal-header">
              <h3><Icon name="file" size={18} /> Detalles de la Notificación</h3>
              <button onClick={cerrarDetalles} className="btn-cerrar"><Icon name="close" size={18}/></button>
            </div>
            
            <div className="modal-body">
              <div className="detalle-grupo">
                <h4><Icon name="file" size={18} /> Información General</h4>
                <div className="detalle-item">
                  <span className="detalle-label">ID:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.id}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Tipo:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.tipoNotificacion || 'N/A'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Prioridad:</span>
                  <span className={`badge prioridad ${obtenerColorPrioridad(notificacionSeleccionada.prioridad)}`}>
                    {notificacionSeleccionada.prioridad || 'N/A'}
                  </span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Estado:</span>
                  <span className="detalle-valor">
                    {notificacionSeleccionada.estado ? ' Activo' : '✕ Inactivo'}
                  </span>
                </div>
              </div>

              <div className="detalle-grupo">
                <h4><Icon name="group" size={18} /> Remitente y Destinatario</h4>
                <div className="detalle-item">
                  <span className="detalle-label">ID Remitente:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.idRemitente || 'N/A'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Email Remitente:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.emailRemitente || 'N/A'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">ID Destinatario:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.idDestinatario || 'N/A'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Email Destinatario:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.emailDestinatario || 'N/A'}</span>
                </div>
              </div>

              <div className="detalle-grupo">
                <h4><Icon name="mail" size={18} /> Contenido</h4>
                <div className="detalle-item">
                  <span className="detalle-label">Asunto:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.asunto || 'Sin asunto'}</span>
                </div>
                <div className="detalle-item mensaje">
                  <span className="detalle-label">Mensaje:</span>
                  <p className="detalle-mensaje">
                    {notificacionSeleccionada.cuerpoMensaje || 'Sin mensaje'}
                  </p>
                </div>
              </div>

              <div className="detalle-grupo">
                <h4><Icon name="book" size={18} /> Curso Relacionado</h4>
                <div className="detalle-item">
                  <span className="detalle-label">Curso:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.cursoRelacionado || 'N/A'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Categoría:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.categoriaCurso || 'N/A'}</span>
                </div>
              </div>

              <div className="detalle-grupo">
                <h4><Icon name="calendar" size={18} /> Fechas</h4>
                <div className="detalle-item">
                  <span className="detalle-label">Fecha Creación:</span>
                  <span className="detalle-valor">{formatearFecha(notificacionSeleccionada.fechaCreacion)}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Fecha Envío:</span>
                  <span className="detalle-valor">{formatearFecha(notificacionSeleccionada.fechaEnvio)}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Hora Envío:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.horaEnvio || 'N/A'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Fecha Entrega:</span>
                  <span className="detalle-valor">{formatearFecha(notificacionSeleccionada.fechaEntrega)}</span>
                </div>
              </div>

              <div className="detalle-grupo">
                <h4><Icon name="settings" size={18} /> Configuración</h4>
                <div className="detalle-item">
                  <span className="detalle-label">Mensaje Enviado:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.mensajeEnviado ? ' Sí' : '✕ No'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Mensaje Leído:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.mensajeLeido ? ' Sí' : '✕ No'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Mensaje Eliminado:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.mensajeEliminado ? ' Sí' : '✕ No'}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Archivos Adjuntos:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.cantidadArchivosAdjuntos || 0}</span>
                </div>
                <div className="detalle-item">
                  <span className="detalle-label">Notificación Emergente:</span>
                  <span className="detalle-valor">{notificacionSeleccionada.notificacionEmergente ? ' Sí' : '✕ No'}</span>
                </div>
              </div>
            </div>

            <div className="modal-footer">
              <button onClick={cerrarDetalles} className="btn-cerrar-modal">
                Cerrar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default ListaNotificaciones;