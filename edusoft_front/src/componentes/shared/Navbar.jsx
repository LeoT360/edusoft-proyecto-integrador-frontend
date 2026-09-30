import { useNavigate, useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import './Navbar.css';
import logoEdusoft from '../../assets/title-edusoft.svg';
import logoWhiteEdusoft from '../../assets/title-white-edusoft.svg';
import Icon from './Icon';

const SECCIONES = [
  { id: 'home', icono: 'home', label: 'Inicio', ruta: '/home' },
  { id: 'usuarios', icono: 'group', label: 'Usuarios', ruta: '/usuarios', soloProfesor: true },
  { id: 'notificaciones', icono: 'bell', label: 'Notificaciones', ruta: '/notificaciones' },
  { id: 'profesores', icono: 'graduation', label: 'Profesores', ruta: '/profesores' },
  { id: 'asistencias', icono: 'clipboard', label: 'Asistencias', ruta: '/asistencias' },
  { id: 'cursos', icono: 'book', label: 'Cursos', ruta: '/cursos' },
  { id: 'notas', icono: 'note', label: 'Notas', ruta: '/notas' },
  { id: 'matricula', icono: 'school', label: 'Matrícula', ruta: '/matricula' },
  { id: 'reportes', icono: 'chart', label: 'Reportes Estadísticos', ruta: '/reportes', soloProfesor: true },
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
            <span className="nav-item-icono"><Icon name={sec.icono} size={19}/></span>
            <span>{sec.label}</span>
          </button>
        );
      })}
    </div>
  );

  return <>
    <aside className="sidebar-desktop">
      <div className="sidebar-brand" onClick={() => redirigir('/home')}>
        <img src={darkMode ? logoWhiteEdusoft : logoEdusoft} alt="Logo EduSoft" />
      </div>
      <div className="sidebar-user"><strong>{usuario?.nombre}</strong><span>{usuario?.rol}</span></div>
      {renderNav()}
      <div className="sidebar-footer">
        <button className="sidebar-action" onClick={() => setDarkMode(v => !v)}><Icon name={darkMode ? "sun" : "moon"} size={18}/><span>{darkMode ? 'Modo claro' : 'Modo oscuro'}</span></button>
        <button className="sidebar-action danger" onClick={salir}><Icon name="logout" size={18}/><span>Cerrar sesión</span></button>
      </div>
    </aside>

    <header className="mobile-navbar">
      <img src={darkMode ? logoWhiteEdusoft : logoEdusoft} alt="Logo EduSoft" className="mobile-logo" onClick={() => redirigir('/home')} />
      <div className="mobile-actions">
        <button className="mobile-theme" onClick={() => setDarkMode(v => !v)} aria-label="Cambiar tema"><Icon name={darkMode ? "sun" : "moon"} size={18}/></button>
        <button className="mobile-menu-button" onClick={() => setMenuAbierto(v => !v)} aria-label={menuAbierto ? 'Cerrar menú' : 'Abrir menú'}>{menuAbierto ? '✕' : '☰'}</button>
      </div>
    </header>

    {menuAbierto && <div className="mobile-menu-overlay">
      <div className="mobile-menu-panel">
        <div className="mobile-menu-header"><div><strong>{usuario?.nombre}</strong><span>{usuario?.rol}</span></div><button onClick={() => setMenuAbierto(false)}><Icon name="close" size={20}/></button></div>
        {renderNav()}
        <div className="mobile-menu-footer"><button className="sidebar-action" onClick={salir}><Icon name="logout" size={18}/><span>Cerrar sesión</span></button></div>
      </div>
    </div>}
  </>;
}

export default Navbar;
