const express = require('express'); // Importa el módulo Express
const cors = require('cors'); // Importa el middleware CORS para permitir solicitudes de otros dominios
const logger = require('morgan'); // Importa el middleware Morgan para registrar solicitudes HTTP
const cookies = require('cookie-parser'); // Importa el middleware Cookie-parser para manejar cookies


// Creamos las rutas de destino
const homeRouter = require('./routes/home'); // Importa las rutas definidas en el archivo home.js


// Creo un objeto de EXPRESS
// Es el que hace la magia en el BACKEND
const app = express(); // Crea una instancia de Express


// Configuración de middlewares
app.use(logger('dev')); // Utiliza Morgan para registrar las solicitudes en modo 'dev'
app.use(cors()); // Utiliza CORS para permitir solicitudes de otros dominios
app.use(cookies()); // Utiliza Cookie-parser para manejar cookies
app.use(express.json()); // Middleware para que Express entienda cuerpos de solicitud en formato JSON
app.use(express.urlencoded({ extended: false })); // Middleware para que Express entienda cuerpos de solicitud URL-encoded


// Manejo de RUTAS
// URL-BASE:  localhost:3000    127.0.0.1:3000

// Ruta base para el homeRouter
//  127.0.0.1:3000/ -> Será manejado por homeRouter


// Puedes definir más rutas aquí sin imoportar el TypeHTTP-Request, por ejemplo:
app.use('/', homeRouter);
// app.use('/aviones', avionesRouter); // Para manejar rutas relacionadas con aviones
// app.use('/aviones/mostrar', avionesMostrarRouter); // Para manejar rutas específicas de aviones/mostrar


// Le permito a otras clases crear objetos de esta aplicación
module.exports = app; // Exporta la instancia de Express para que otros módulos puedan utilizarla
