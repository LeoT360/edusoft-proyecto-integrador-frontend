import { useNavigate, useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import './Navbar.css';
import logoEdusoft from '../../assets/title-edusoft.svg';

const SECCIONES = [
  { id: 'home', icono: '🏠', label: 'Inicio', ruta: '/home' },
  { id: 'usuarios', icono: '👤', label: 'Usuarios', ruta: '/usuarios', soloProfesor: true },
  { id: 'notificaciones', icono: '📧', label: 'Notificaciones', ruta: '/notificaciones' },
  { id: 'profesores', icono: '🎓', label: 'Profesores', ruta: '/profesores' },
  { id: 'asistencias', icono: '📋', label: 'Asistencias', ruta: '/asistencias' },
  { id: 'cursos', icono: '📖', label: 'Cursos', ruta: '/cursos' },
  { id: 'notas', icono: '📝', label: 'Notas', ruta: '/notas' },
  { id: 'matricula', icono: '🏫', label: 'Matrícula', ruta: '/matricula' },
  { id: 'reportes', icono: '📊', label: 'Reportes Estadísticos', ruta: '/reportes', soloProfesor: true },
];

function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();
  const [menuAbierto, setMenuAbierto] = useState(false);
  const [darkMode, setDarkMode] = useState(() => localStorage.getItem('edusoft-dark') === 'true');
  const usuario = JSON.parse(localStorage.getItem('usuario'));
  const esProfesor = usuario?.rol === 'Profesor';

  useEffect(() => {
    document.body.classList.toggle('dark', darkMode);
    localStorage.setItem('edusoft-dark', darkMode);
  }, [darkMode]);

  useEffect(() => { setMenuAbierto(false); }, [location.pathname]);

  const salir = () => { localStorage.removeItem('usuario'); navigate('/'); };
  const redirigir = (ruta) => navigate(ruta);
  const activo = (ruta) => location.pathname === ruta || location.pathname.startsWith(ruta + '/');

  const renderNav = () => (
    <div className="nav-scroll-area">
      {SECCIONES.map(sec => {
        if (sec.soloProfesor && !esProfesor) return null;
        return (
          <button
            key={sec.id}
            className={`nav-item-directo ${activo(sec.ruta) ? 'activo' : ''}`}
            onClick={() => redirigir(sec.ruta)}
          >
            <span className="nav-item-icono">{sec.icono}</span>
            <span>{sec.label}</span>
          </button>
        );
      })}
    </div>
  );

  return <>
    <aside className="sidebar-desktop">
      <div className="sidebar-brand" onClick={() => redirigir('/home')}>
        <img src={logoEdusoft} alt="Logo EduSoft" />
      </div>
      <div className="sidebar-user"><strong>{usuario?.nombre}</strong><span>{usuario?.rol}</span></div>
      {renderNav()}
      <div className="sidebar-footer">
        <button className="sidebar-action" onClick={() => setDarkMode(v => !v)}>{darkMode ? '☀️' : '🌙'}<span>{darkMode ? 'Modo claro' : 'Modo oscuro'}</span></button>
        <button className="sidebar-action danger" onClick={salir}>🚪<span>Cerrar sesión</span></button>
      </div>
    </aside>

    <header className="mobile-navbar">
      <img src={logoEdusoft} alt="Logo EduSoft" className="mobile-logo" onClick={() => redirigir('/home')} />
      <div className="mobile-actions">
        <button className="mobile-theme" onClick={() => setDarkMode(v => !v)} aria-label="Cambiar tema">{darkMode ? '☀️' : '🌙'}</button>
        <button className="mobile-menu-button" onClick={() => setMenuAbierto(v => !v)} aria-label={menuAbierto ? 'Cerrar menú' : 'Abrir menú'}>{menuAbierto ? '✕' : '☰'}</button>
      </div>
    </header>

    {menuAbierto && <div className="mobile-menu-overlay">
      <div className="mobile-menu-panel">
        <div className="mobile-menu-header"><div><strong>{usuario?.nombre}</strong><span>{usuario?.rol}</span></div><button onClick={() => setMenuAbierto(false)}>✕</button></div>
        {renderNav()}
        <div className="mobile-menu-footer"><button className="sidebar-action" onClick={salir}>🚪<span>Cerrar sesión</span></button></div>
      </div>
    </div>}
  </>;
}

export default Navbar;
