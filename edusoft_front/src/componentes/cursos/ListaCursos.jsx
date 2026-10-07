import { BookOpen, ClipboardList, Clock, FileText, GraduationCap, Lock, Mail, Star, Users, X, Zap } from 'lucide-react';
// ====================================
// LISTA CURSOS - UNIFICADA
// Profesor: CRUD completo
// Estudiante: vista de catálogo (solo lectura)
// ====================================

import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { cursoService } from '../../services/cursoService';
import './Cursos.css';

function ListaCursos() {
  const navigate   = useNavigate();
  const usuario    = JSON.parse(localStorage.getItem('usuario'));
  const esProfesor = usuario?.rol === 'Profesor';

  const [cursos,       setCursos]       = useState([]);
  const [filtrados,    setFiltrados]    = useState([]);
  const [busqueda,     setBusqueda]     = useState('');
  const [filtraTipo,   setFiltraTipo]   = useState('');
  const [cargando,     setCargando]     = useState(true);
  const [error,        setError]        = useState('');
  const [seleccionado, setSeleccionado] = useState(null);

  useEffect(() => { cargar(); }, []);

  useEffect(() => {
    let lista = cursos;

    if (busqueda) {
      const q = busqueda.toLowerCase();
      lista = lista.filter(c =>
        (c.titulo      || '').toLowerCase().includes(q) ||
        (c.maestro     || '').toLowerCase().includes(q) ||
        (c.descripcion || '').toLowerCase().includes(q) ||
        String(c.id    || '').includes(q)
      );
    }

    if (filtraTipo) {
      lista = lista.filter(c => c.tipoCurso === filtraTipo);
    }

    setFiltrados(lista);
  }, [busqueda, filtraTipo, cursos]);

  const cargar = async () => {
    setCargando(true);
    setError('');
    try {
      const data = await cursoService.listarTodos();
      setCursos(data);
      setFiltrados(data);
    } catch {
      setError('No se pudieron cargar los cursos. Verifica que el backend esté corriendo en el puerto 8080.');
    } finally {
      setCargando(false);
    }
  };

  // Extrae tipos únicos para el filtro
  const tiposUnicos = [...new Set(cursos.map(c => c.tipoCurso).filter(Boolean))];

  // Estrellas de calificación
  const renderEstrellas = (cal) => {
    if (!cal) return null;
    const llenas = Math.round(cal);
    return (
      <span className="estrellas-iconos">
        {Array.from({ length: 5 }, (_, i) => (
          <Star key={i} size={15} fill={i < llenas ? 'currentColor' : 'none'} />
        ))}
      </span>
    );
  };

  if (cargando) return (
    <div className="cursos-container">
      <div className="cargando-container">
        <div className="spinner-global" />
        <p>Cargando cursos...</p>
      </div>
    </div>
  );

  return (
    <div className="cursos-container">

      {/* ENCABEZADO */}
      <div className="cursos-header">
        <div className="cursos-header-texto">
          <h2><BookOpen size={18} /> {esProfesor ? 'Gestión de Cursos' : 'Catálogo de Cursos'}</h2>
          <p className="cursos-subtitulo">
            {esProfesor
              ? `${cursos.length} curso${cursos.length !== 1 ? 's' : ''} registrado${cursos.length !== 1 ? 's' : ''}`
              : 'Explora los cursos disponibles'}
          </p>
        </div>
        {esProfesor && (
          <button className="btn-nuevo-curso" onClick={() => navigate('/cursos/crear')}>
             Nuevo Curso
          </button>
        )}
      </div>

      {/* FILTROS */}
      <div className="cursos-filtros">
        <input
          className="cursos-search"
          placeholder=" Buscar por título, maestro o descripción..."
          value={busqueda}
          onChange={e => setBusqueda(e.target.value)}
        />
        {tiposUnicos.length > 0 && (
          <select
            className="cursos-select-filtro"
            value={filtraTipo}
            onChange={e => setFiltraTipo(e.target.value)}
          >
            <option value="">Todos los tipos</option>
            {tiposUnicos.map(t => (
              <option key={t} value={t}>{t}</option>
            ))}
          </select>
        )}
      </div>

      {error && <div className="cursos-error">{error}</div>}

      {!error && filtrados.length === 0 && (
        <div className="cursos-vacio">
          <span className="cursos-vacio-icono"><Mail size={18} /></span>
          <p>No se encontraron cursos con esos criterios.</p>
          {(busqueda || filtraTipo) && (
            <button className="btn-limpiar-filtros"
              onClick={() => { setBusqueda(''); setFiltraTipo(''); }}>
              Limpiar filtros
            </button>
          )}
        </div>
      )}

      {/* GRID DE TARJETAS */}
      <div className="cursos-grid">
        {filtrados.map(curso => (
          <TarjetaCurso
            key={curso.id}
            curso={curso}
            esProfesor={esProfesor}
            onVer={() => setSeleccionado(curso)}
            onEditar={() => navigate(`/cursos/editar/${curso.id}`)}
            renderEstrellas={renderEstrellas}
          />
        ))}
      </div>

      {/* MODAL DE DETALLE */}
      {seleccionado && (
        <ModalDetalleCurso
          curso={seleccionado}
          esProfesor={esProfesor}
          onCerrar={() => setSeleccionado(null)}
          onEditar={() => { setSeleccionado(null); navigate(`/cursos/editar/${seleccionado.id}`); }}
          renderEstrellas={renderEstrellas}
        />
      )}
    </div>
  );
}

// ── TARJETA ──────────────────────────────────
function TarjetaCurso({ curso, esProfesor, onVer, onEditar, renderEstrellas }) {
  const presencial = curso.presencialidad;

  return (
    <div className="curso-card" onClick={onVer}>
      {/* Franja de tipo */}
      {curso.tipoCurso && (
        <div className="curso-tipo-franja">{curso.tipoCurso}</div>
      )}

      {/* Badge presencialidad */}
      <span className={`badge-modalidad ${presencial ? 'badge-presencial' : 'badge-virtual'}`}>
        {presencial ? ' Presencial' : ' Virtual'}
      </span>

      <div className="curso-card-body">
        <h3 className="curso-titulo">{curso.titulo || 'Sin título'}</h3>

        {curso.maestro && (
          <p className="curso-maestro"><GraduationCap size={18} /> {curso.maestro}</p>
        )}

        {curso.descripcion && (
          <p className="curso-descripcion">
            {curso.descripcion.length > 90
              ? curso.descripcion.substring(0, 90) + '...'
              : curso.descripcion}
          </p>
        )}

        {/* Metadata */}
        <div className="curso-meta">
          {curso.duracion != null && (
            <span className="curso-meta-item"><Clock size={18} /> {curso.duracion}h</span>
          )}
          {curso.intensidad != null && (
            <span className="curso-meta-item"><Zap size={18} /> {curso.intensidad} hrs/sem</span>
          )}
          {curso.estudiantes != null && (
            <span className="curso-meta-item"><Users size={18} /> {curso.estudiantes}</span>
          )}
        </div>

        {/* Calificación */}
        {curso.calificacion != null && (
          <div className="curso-calificacion">
            <span className="estrellas">{renderEstrellas(curso.calificacion)}</span>
            <span className="cal-numero">{curso.calificacion.toFixed(1)}</span>
          </div>
        )}
      </div>

      {/* Acciones */}
      <div className="curso-card-footer" onClick={e => e.stopPropagation()}>
        <button className="btn-curso-ver" onClick={onVer}>
           Ver detalle
        </button>
        {esProfesor && (
          <button className="btn-curso-editar" onClick={onEditar}>
             Editar
          </button>
        )}
      </div>
    </div>
  );
}

// ── MODAL DETALLE ─────────────────────────────
function ModalDetalleCurso({ curso, esProfesor, onCerrar, onEditar, renderEstrellas }) {
  return (
    <div className="modal-overlay-curso" onClick={onCerrar}>
      <div className="modal-curso" onClick={e => e.stopPropagation()}>

        {/* Header */}
        <div className="modal-curso-header">
          <div>
            <h3>{curso.titulo || 'Sin título'}</h3>
            {curso.tipoCurso && <span className="modal-tipo-badge">{curso.tipoCurso}</span>}
          </div>
          <button className="btn-cerrar-modal-curso" onClick={onCerrar}><X size={18} /></button>
        </div>

        {/* Body */}
        <div className="modal-curso-body">

          {/* Info general - todos la ven */}
          <section className="modal-curso-seccion">
            <h4><ClipboardList size={18} /> Información General</h4>
            <FilaDetalle label="Maestro"       valor={curso.maestro} />
            <FilaDetalle label="Modalidad"     valor={curso.presencialidad ? 'Presencial' : 'Virtual'} />
            <FilaDetalle label="Tipo"          valor={curso.tipoCurso} />
            <FilaDetalle label="Duración"      valor={curso.duracion != null ? `${curso.duracion} horas` : null} />
            <FilaDetalle label="Intensidad"    valor={curso.intensidad != null ? `${curso.intensidad} hrs/semana` : null} />
            <FilaDetalle label="Capítulos"     valor={curso.capitulosCurso} />
            <FilaDetalle label="Estudiantes"   valor={curso.estudiantes} />

            {curso.calificacion != null && (
              <div className="fila-detalle">
                <span className="fila-label">Calificación:</span>
                <span className="estrellas-modal">
                  {renderEstrellas(curso.calificacion)} ({curso.calificacion.toFixed(1)})
                </span>
              </div>
            )}
          </section>

          {/* Descripción */}
          {curso.descripcion && (
            <section className="modal-curso-seccion">
              <h4><FileText size={18} /> Descripción</h4>
              <p className="modal-descripcion">{curso.descripcion}</p>
            </section>
          )}

          {/* Info solo para profesores */}
          {esProfesor && (
            <section className="modal-curso-seccion">
              <h4><Lock size={18} /> Datos Administrativos</h4>
              <FilaDetalle label="Lugar de realización" valor={curso.lugarRealizacion} />
              <FilaDetalle label="Fecha de creación"    valor={curso.fechaCreacion} />
              <FilaDetalle label="Fecha de finalización" valor={curso.fechaFinalizacion} />
              {curso.comentarios && (
                <div className="fila-detalle fila-bloque">
                  <span className="fila-label">Comentarios:</span>
                  <p className="fila-valor-bloque">{curso.comentarios}</p>
                </div>
              )}
            </section>
          )}

          {!esProfesor && (
            <div className="modal-aviso-estudiante-curso">
               Algunos datos administrativos del curso son visibles solo para docentes.
            </div>
          )}
        </div>

        {/* Footer con acción editar para profesor */}
        {esProfesor && (
          <div className="modal-curso-footer">
            <button className="btn-curso-editar-modal" onClick={onEditar}>
               Editar este curso
            </button>
          </div>
        )}

      </div>
    </div>
  );
}

function FilaDetalle({ label, valor }) {
  if (valor === null || valor === undefined || valor === '') return null;
  return (
    <div className="fila-detalle">
      <span className="fila-label">{label}:</span>
      <span className="fila-valor">{String(valor)}</span>
    </div>
  );
}

export default ListaCursos;
