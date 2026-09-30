import Icon from '../shared/Icon';
import React from 'react';
import { useNavigate } from 'react-router-dom';
import logoEdusoft from '../../assets/title-edusoft.svg';
import logoWhiteEdusoft from '../../assets/title-white-edusoft.svg';
import './Inicio.css';

const imagenesTech = {
  principal: 'https://images.pexels.com/photos/12899151/pexels-photo-12899151.jpeg?cs=srgb&dl=pexels-mizunokozuki-12899151.jpg&fm=jpg',
  codigo: 'https://images.pexels.com/photos/6424590/pexels-photo-6424590.jpeg?cs=srgb&dl=pexels-nemuel-6424590.jpg&fm=jpg',
  desarrollo: 'https://images.pexels.com/photos/1181673/pexels-photo-1181673.jpeg?cs=srgb&dl=pexels-divinetechygirl-1181673.jpg&fm=jpg',
};

function Inicio() {
  const logoActual = document.body.classList.contains('dark') ? logoWhiteEdusoft : logoEdusoft;
  const navigate = useNavigate();

  const caracteristicas = [
    { icono: <Icon name="group" size={21} />, titulo: 'Usuarios', descripcion: 'Roles y perfiles institucionales.' },
    { icono: <Icon name="mail" size={21} />, titulo: 'Notificaciones', descripcion: 'Comunicación y seguimiento.' },
    { icono: <Icon name="graduation" size={21} />, titulo: 'Profesores', descripcion: 'Gestión del equipo docente.' },
    { icono: <Icon name="note" size={21} />, titulo: 'Notas', descripcion: 'Calificaciones por período.' },
    { icono: <Icon name="user" size={21} />, titulo: 'Asistencias', descripcion: 'Control de asistencia.' },
    { icono: <Icon name="school" size={21} />, titulo: 'Matrícula', descripcion: 'Administración académica.' },
  ];

  return (
    <div className="inicio-container">
      <main className="inicio-content">
        <section className="inicio-hero">
          <div className="inicio-hero-copy">
            <div className="inicio-kicker">
              <span className="inicio-kicker-line" />
              Plataforma académica
            </div>

            <img src={logoActual} alt="EduSoft" className="inicio-logo" />

            <p className="inicio-descripcion">
              Una plataforma unificada para administrar usuarios, profesores,
              cursos, notas, matrículas, asistencias y reportes.
            </p>

            <div className="inicio-botones">
              <button className="btn-inicio btn-primario" onClick={() => navigate('/login')}>
                Iniciar sesión
                <Icon name="arrow" size={17} />
              </button>
              <button className="btn-inicio btn-secundario" onClick={() => navigate('/registro')}>
                Crear cuenta
              </button>
            </div>
          </div>

          <div className="inicio-hero-visual">
            <div className="inicio-image-main">
              <img src={imagenesTech.principal} alt="Personas trabajando con tecnología" />
            </div>
            <div className="inicio-image-small inicio-image-small-one">
              <img src={imagenesTech.codigo} alt="Código en un computador" loading="lazy" />
            </div>
            <div className="inicio-image-small inicio-image-small-two">
              <img src={imagenesTech.desarrollo} alt="Entorno de desarrollo" loading="lazy" />
            </div>
            <div className="inicio-blue-block">
              <span>TECNOLOGÍA</span>
              <strong>EDUSOFT</strong>
            </div>
          </div>
        </section>

        <section className="inicio-modulos">
          <div className="inicio-section-heading">
            <div>
              <h2>Herramientas para la gestión académica</h2>
            </div>
          </div>

          <div className="inicio-modulos-grid">
            {caracteristicas.map((c, i) => (
              <article className="caracteristica" key={i}>
                <div className="caracteristica-icono">{c.icono}</div>
                <div>
                  <h3>{c.titulo}</h3>
                  <p>{c.descripcion}</p>
                </div>
              </article>
            ))}
          </div>
        </section>

        <section className="inicio-cierre">
          <div className="inicio-cierre-image">
            <img src={imagenesTech.codigo} alt="Tecnología y programación" loading="lazy" />
          </div>
          <div className="inicio-cierre-copy">
            <span className="inicio-eyebrow">PLATAFORMA EDUSOFT</span>
            <h2>Información organizada para tomar mejores decisiones.</h2>
            <button className="inicio-text-button" onClick={() => navigate('/login')}>
              Acceder a la plataforma
              <Icon name="arrow" size={17} />
            </button>
          </div>
        </section>
      </main>

      <footer className="inicio-footer">
        <span>Proyecto Integrador EduSoft</span>
        <span>2026</span>
      </footer>
    </div>
  );
}

export default Inicio;
