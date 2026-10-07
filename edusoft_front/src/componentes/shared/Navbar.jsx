import { useNavigate, useLocation } from 'react-router-dom';
import { useState, useEffect } from 'react';
import './Navbar.css';
import logoEdusoft from '../../assets/title-edusoft.svg';
import logoWhiteEdusoft from '../../assets/title-white-edusoft.svg';
import { Bell, BookOpen, ChartLine, ClipboardList, FileText, GraduationCap, House, LogOut, Menu, Moon, School, Sun, Users, X } from 'lucide-react';

const SECCIONES = [
  { id: 'home', icono: House, label: 'Inicio', ruta: '/home' },
  { id: 'usuarios', icono: Users, label: 'Usuarios', ruta: '/usuarios', soloProfesor: true },
  { id: 'notificaciones', icono: Bell, label: 'Notificaciones', ruta: '/notificaciones' },
  { id: 'profesores', icono: GraduationCap, label: 'Profesores', ruta: '/profesores' },
  { id: 'asistencias', icono: ClipboardList, label: 'Asistencias', ruta: '/asistencias' },
  { id: 'cursos', icono: BookOpen, label: 'Cursos', ruta: '/cursos' },
  { id: 'notas', icono: FileText, label: 'Notas', ruta: '/notas' },
  { id: 'matricula', icono: School, label: 'Matrícula', ruta: '/matricula' },
  { id: 'reportes', icono: ChartLine, label: 'Reportes Estadísticos', ruta: '/reportes', soloProfesor: true },
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
        const IconoSeccion = sec.icono;
        return (
          <button
            key={sec.id}
            className={`nav-item-directo ${activo(sec.ruta) ? 'activo' : ''}`}
            onClick={() => redirigir(sec.ruta)}
          >
            <span className="nav-item-icono"><IconoSeccion size={19}/></span>
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
        <button className="sidebar-action" onClick={() => setDarkMode(v => !v)}>{darkMode ? <Sun size={18}/> : <Moon size={18}/>}<span>{darkMode ? 'Modo claro' : 'Modo oscuro'}</span></button>
        <button className="sidebar-action danger" onClick={salir}><LogOut size={18} /><span>Cerrar sesión</span></button>
      </div>
    </aside>

    <header className="mobile-navbar">
      <img src={darkMode ? logoWhiteEdusoft : logoEdusoft} alt="Logo EduSoft" className="mobile-logo" onClick={() => redirigir('/home')} />
      <div className="mobile-actions">
        <button className="mobile-theme" onClick={() => setDarkMode(v => !v)} aria-label="Cambiar tema">{darkMode ? <Sun size={18}/> : <Moon size={18}/>}</button>
        <button className="mobile-menu-button" onClick={() => setMenuAbierto(v => !v)} aria-label={menuAbierto ? 'Cerrar menú' : 'Abrir menú'}>{menuAbierto ? <X size={22}/> : <Menu size={22}/>}</button>
      </div>
    </header>

    {menuAbierto && <div className="mobile-menu-overlay">
      <div className="mobile-menu-panel">
        <div className="mobile-menu-header"><div><strong>{usuario?.nombre}</strong><span>{usuario?.rol}</span></div><button onClick={() => setMenuAbierto(false)}><X size={20} /></button></div>
        {renderNav()}
        <div className="mobile-menu-footer"><button className="sidebar-action" onClick={salir}><LogOut size={18} /><span>Cerrar sesión</span></button></div>
      </div>
    </div>}
  </>;
}

export default Navbar;
