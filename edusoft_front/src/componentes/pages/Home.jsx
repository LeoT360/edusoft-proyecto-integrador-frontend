import { ArrowRight, BookOpen, ChartLine, ClipboardList, FileText, GraduationCap, Info, Lock, Mail, Pencil, School, Users, Zap } from 'lucide-react';
// ====================================
// PÁGINA HOME - DASHBOARD PRINCIPAL
// Sistema de roles: Profesor / Estudiante
// ====================================

import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css';

function Home() {
  const navigate = useNavigate();
  const usuario = JSON.parse(localStorage.getItem('usuario'));
  const esProfesor = usuario?.rol === 'Profesor';

  // ====================================
  // TARJETAS DE ACCESO RÁPIDO
  // disponible: si aparece en el dashboard
  // soloProfesor: muestra badge y restringe acceso
  // proximamente: módulo aún no implementado
  // ====================================
  const tarjetas = [
    // ===== MÓDULO ACTIVO: USUARIOS =====
    {
      titulo: 'Usuarios',
      descripcion: 'Ver lista de usuarios registrados en el sistema.',
      icono: <Users size={22} />,
      ruta: '/usuarios',
      color: 'azul',
      disponible: esProfesor,   //  Solo profesores ven esta tarjeta
      soloProfesor: true,
      proximamente: false,
    },

    // ===== MÓDULO ACTIVO: NOTIFICACIONES =====
    {
      titulo: 'Ver Notificaciones',
      descripcion: 'Consultar notificaciones recibidas.',
      icono: <Mail size={22} />,
      ruta: '/notificaciones',
      color: 'cyan',
      disponible: true,
      soloProfesor: true,
      proximamente: false,
    },
    {
      titulo: 'Nueva Notificación',
      descripcion: 'Crear y enviar una notificación a usuarios.',
      icono: <Pencil size={22} />,
      ruta: '/notificaciones/crear',
      color: 'dorado',
      disponible: esProfesor,   //  Solo profesores ven esta tarjeta
      soloProfesor: true,
      proximamente: false,
    },

    // ===== MÓDULO ACTIVO: PROFESORES =====
    {
      titulo: 'Profesores',
      descripcion: 'Gestionar información de profesores del sistema.',
      icono: <GraduationCap size={22} />,
      ruta: '/profesores',
      color: 'azul',
      disponible: true,
      soloProfesor: false,
      proximamente: false,
    },

    //====== Cursos ========
    {
      titulo: 'Cursos',
      descripcion: 'Administrar y gestionar pertenencia a los cursos de los estudiantes.',
      icono: <BookOpen size={22} />,
      color: 'dorado',
      ruta: '/cursos',
      disponible: true,
      soloProfesor: false,
      proximamente: false,
    },

    // ===== MÓDULO NOTAS =====
    {
      titulo: 'Notas',
      descripcion: 'Consultar y gestionar calificaciones de estudiantes.',
      icono: <FileText size={22} />,
      ruta: '/notas',
      color: 'cyan',
      disponible: true,
      soloProfesor: true,
      proximamente: false, 
    },

    // ===== MÓDULO PRÓXIMO: ASISTENCIAS =====
    {
      titulo: 'Asistencias',
      descripcion: 'Registrar y consultar asistencias por curso.',
      icono: <ClipboardList size={22} />,
      ruta: '/asistencias',
      color: 'azul',
      disponible: true,
      soloProfesor: true,
      proximamente: false,
    },

    // ===== MÓDULO PRÓXIMO: MATRÍCULA =====
    {
      titulo: 'Matrícula',
      descripcion: 'Administrar matrículas y cursos del período.',
      icono: <School size={22} />,
      ruta: '/matricula',
      color: 'dorado',
      disponible: true,
      soloProfesor: false,
      proximamente: false,
    },

    // ===== MÓDULO ACTIVO: REPORTES (solo profesor) =====
    {
      titulo: 'Reportes Estadísticos',
      descripcion: 'Visualizar estadísticas y reportes del sistema.',
      icono: <ChartLine size={22} />,
      ruta: '/reportes',
      color: 'azul',
      disponible: esProfesor,   //  Solo profesores ven esta tarjeta
      soloProfesor: true,
      proximamente: false,
    },
  ];

  const irA = (tarjeta) => {
    if (tarjeta.proximamente) return; // no navegar si aún no está listo
    navigate(tarjeta.ruta);
  };

  return (
    <div className="home-container">
      <div className="home-content">

        {/* ===== ENCABEZADO ===== */}
        <div className="home-header">
          <h1>¡Bienvenido, {usuario?.nombre}!</h1>
          <p className="home-rol">
            Rol: <span className="badge-rol-home">{usuario?.rol}</span>
          </p>
        </div>

        {/* ===== DESCRIPCIÓN SEGÚN ROL ===== */}
        <div className="home-descripcion">
          <p>
            {esProfesor
              ? 'Como profesor, tienes acceso completo para gestionar usuarios, notificaciones, notas, matrículas y reportes.'
              : 'Como estudiante, puedes consultar usuarios, ver las notificaciones que te han enviado, tus notas y matrícula.'}
          </p>
        </div>

        {/* ===== TARJETAS DE ACCESO RÁPIDO ===== */}
        <div className="home-tarjetas">
          {tarjetas.map((tarjeta, index) => {
            if (!tarjeta.disponible) return null;

            return (
              <div
                key={index}
                className={`tarjeta tarjeta-${tarjeta.color} ${tarjeta.proximamente ? 'tarjeta-proximamente' : ''}`}
                onClick={() => irA(tarjeta)}
                style={{ cursor: tarjeta.proximamente ? 'default' : 'pointer' }}
              >
                <div className="tarjeta-icono">{tarjeta.icono}</div>
                <h3>{tarjeta.titulo}</h3>
                <p>{tarjeta.descripcion}</p>

                {tarjeta.soloProfesor && (
                  <span className="badge-profesor">Solo Profesores</span>
                )}

                {tarjeta.proximamente && (
                  <span className="badge-proximamente">Próximamente</span>
                )}

                {!tarjeta.proximamente && (
                  <div className="tarjeta-flecha"><ArrowRight size={18} /></div>
                )}
              </div>
            );
          })}
        </div>

        {/* ===== AVISO PARA ESTUDIANTES ===== */}
        {!esProfesor && (
          <div className="info-estudiante">
            <div className="info-icono"><Info size={18} /></div>
            <p>
              <strong>Nota:</strong> Como estudiante, puedes ver notificaciones pero no crearlas ni editarlas.
              Si necesitas enviar una notificación, contacta a tu profesor.
            </p>
          </div>
        )}

        {/* ===== ESTADÍSTICAS / ICONOS INFORMATIVOS ===== */}
        <div className="home-estadisticas">
          <div className="estadistica">
            <div className="estadistica-numero"><ChartLine size={18} /></div>
            <p>Sistema Integrado</p>
          </div>
          <div className="estadistica">
            <div className="estadistica-numero"><Lock size={18} /></div>
            <p>Acceso Seguro por Roles</p>
          </div>
          <div className="estadistica">
            <div className="estadistica-numero"><Zap size={18} /></div>
            <p>Tiempo Real</p>
          </div>
        </div>

      </div>
    </div>
  );
}

export default Home;