import { Calendar, ChartLine, ClipboardList, Mail, School } from 'lucide-react';
// ====================================
// LISTA MATRÍCULAS
// Profesor : ve todas + acceso a FormularioMatricula
// Estudiante: ve solo sus registros (filtra por correo o nombre)
// ====================================

import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { matriculaService } from '../../services/matriculaService';
import './Matricula.css';

function ListaMatricula() {
  const navigate   = useNavigate();
  const usuario    = JSON.parse(localStorage.getItem('usuario'));
  const esProfesor = usuario?.rol === 'Profesor';

  const [matriculas, setMatriculas] = useState([]);
  const [filtradas,  setFiltradas]  = useState([]);
  const [busqueda,   setBusqueda]   = useState('');
  const [cargando,   setCargando]   = useState(true);
  const [error,      setError]      = useState('');

  useEffect(() => { cargar(); }, []);

  useEffect(() => {
    const q = busqueda.toLowerCase();
    setFiltradas(
      matriculas.filter(m =>
        (m.nombre    || '').toLowerCase().includes(q) ||
        (m.correo    || '').toLowerCase().includes(q) ||
        (m.documento || '').toLowerCase().includes(q) ||
        String(m.id  || '').includes(q)
      )
    );
  }, [busqueda, matriculas]);

  const cargar = async () => {
    setCargando(true);
    setError('');
    try {
      let data = await matriculaService.listarTodas();
      // Estudiante solo ve sus propios registros
      if (!esProfesor && usuario) {
        data = data.filter(m =>
          (m.correo || '').toLowerCase() === (usuario.correo || '').toLowerCase() ||
          (m.nombre || '').toLowerCase().includes((usuario.nombre || '').toLowerCase())
        );
      }
      setMatriculas(data);
      setFiltradas(data);
    } catch {
      setError('No se pudieron cargar las matrículas. Verifica que el backend esté corriendo.');
    } finally {
      setCargando(false);
    }
  };

  const formatFecha = (fecha) => {
    if (!fecha) return '—';
    return new Date(fecha + 'T00:00:00').toLocaleDateString('es-CO', {
      day: '2-digit', month: 'short', year: 'numeric'
    });
  };

  const formatValor = (valor) => {
    if (valor == null) return '—';
    return new Intl.NumberFormat('es-CO', {
      style: 'currency', currency: 'COP', minimumFractionDigits: 0
    }).format(valor);
  };

  if (cargando) return (
    <div className="matricula-container">
      <div className="cargando-container">
        <div className="spinner-global" />
        <p>Cargando matrículas...</p>
      </div>
    </div>
  );

  const totalValor = filtradas.reduce((acc, m) => acc + (m.valorMatricula || 0), 0);

  return (
    <div className="matricula-container">

      {/* ENCABEZADO */}
      <div className="matricula-header">
        <div>
          <h2><School size={18} /> {esProfesor ? 'Gestión de Matrículas' : 'Mi Matrícula'}</h2>
          <p className="matricula-subtitulo">
            {esProfesor
              ? 'Consulta y administra todos los registros de matrícula'
              : 'Registros asociados a tu cuenta'}
          </p>
        </div>
        {esProfesor && (
          <button
            className="btn-nueva-matricula"
            onClick={() => navigate('/matricula/crear')}
          >
             Nueva Matrícula
          </button>
        )}
      </div>

      {/* KPIs */}
      <div className="matricula-kpis">
        <div className="kpi-mat kpi-mat-total">
          <span className="kpi-mat-num">{filtradas.length}</span>
          <span className="kpi-mat-lbl"><ClipboardList size={18} /> Registros</span>
        </div>
        {esProfesor && (
          <div className="kpi-mat kpi-mat-valor">
            <span className="kpi-mat-num">{formatValor(totalValor)}</span>
            <span className="kpi-mat-lbl"><ChartLine size={18} /> Total recaudado</span>
          </div>
        )}
        <div className="kpi-mat kpi-mat-fecha">
          <span className="kpi-mat-num">
            {filtradas.length > 0
              ? formatFecha(filtradas[filtradas.length - 1]?.fechaMatricula)
              : '—'}
          </span>
          <span className="kpi-mat-lbl"><Calendar size={18} /> Último registro</span>
        </div>
      </div>

      {error && <div className="matricula-error">{error}</div>}

      {/* BUSCADOR */}
      <div className="matricula-toolbar">
        <input
          className="matricula-search"
          placeholder=" Buscar por nombre, correo o documento..."
          value={busqueda}
          onChange={e => setBusqueda(e.target.value)}
        />
      </div>

      {/* TABLA */}
      <div className="matricula-tabla-wrap">
        {filtradas.length === 0 ? (
          <div className="matricula-vacio">
            <span><Mail size={18} /></span>
            <p>No se encontraron registros de matrícula.</p>
            {esProfesor && (
              <button
                className="btn-nueva-matricula-vacio"
                onClick={() => navigate('/matricula/crear')}
              >
                Registrar la primera
              </button>
            )}
          </div>
        ) : (
          <table className="matricula-tabla">
            <thead>
              <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Documento</th>
                <th>Correo</th>
                <th>Fecha Matrícula</th>
                <th>Valor</th>
                {esProfesor && <th>Acciones</th>}
              </tr>
            </thead>
            <tbody>
              {filtradas.map(m => (
                <tr key={m.id}>
                  <td className="td-id">{m.id}</td>
                  <td className="td-nombre">{m.nombre}</td>
                  <td>{m.documento}</td>
                  <td className="td-correo">{m.correo}</td>
                  <td>{formatFecha(m.fechaMatricula)}</td>
                  <td className="td-valor">{formatValor(m.valorMatricula)}</td>
                  {esProfesor && (
                    <td className="td-acciones">
                      <button
                        className="btn-mat-editar"
                        onClick={() => navigate(`/matricula/editar/${m.id}`)}
                      >
                         Editar
                      </button>
                    </td>
                  )}
                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>

    </div>
  );
}

export default ListaMatricula;
