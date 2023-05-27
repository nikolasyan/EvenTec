

function pageHome(req, res) {
  return res.render('index.html')
}
function pageEvents(req, res) {
  const filters = req.query
  return res.render('event.html', { events, filters })
}
function pageCadastro(req, res) {
  return res.render('form.html')
}
function pageLogin(req, res) {
  return res.render('login.html')
}
function pageMeusEventos(req, res) {
  return res.render('myEvent.html')
}

const express = require('express')
const bodyParser = require('body-parser');
const server =  express()
server.use(bodyParser.urlencoded({ extended: false }));
server.use(bodyParser.json());
server.use(express.json())



const nunjucks = require('nunjucks')
const { eventRouter } = require('./routes/event.router')
nunjucks.configure('src/views', {
  express: server,
  noCache: true,
})

server.post('/cadastro', () => {
  console.log(req)
  res.json({
    status: 200,
  })
})

server
.use(express.static('public'))
  .get('/', pageHome)
  .get('/cadastrar', pageCadastro)
  .get('/pesquisar', pageEvents)
  .get('/login', pageLogin)
  .get('/meusEventos', pageMeusEventos)  
  .listen(3000)
  server.use(eventRouter)
  