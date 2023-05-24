const events = [
  {
    img: '',
    nome: 'Linguagem educacional',
    categoria: 'Educação de Linguagem',
    sobre:
      'Palestra relativa a Linguagem educacional de diversos alunos na universidade, sitando sobre o modo comportamental de Linguagem dita no dia a dia',
    data: '25/03/24',
    hora: '14:00',
  },
  {
    img: '',
    nome: 'Congresso Nacional da Educação nas Universidade',
    categoria: 'Direitos Humanos',
    sobre:
      'Palestra referentes a educação de direitos dos alunos que estão se preparando para o mestrado explicando a maneira de se envolver na universidade nacional',
    data: '09/07/24',
    hora: '13:30',
  },
  {
    img: '',
    nome: 'Qualidade de Ensino Universitário',
    categoria: 'Educação de Qualidade',
    sobre:
      'Palestra referente a Qualidade de Ensino, orientando os alunos a como desenvolver um aprendizado qualificado na universidade publica',
    data: '13/06/24',
    hora: '12:00',
  },
  {
    img: '',
    nome: 'Educação de Língua de Sinais',
    categoria: 'Educação a PCD',
    sobre:
      'Palestra envolvente para educação de pessoas PCD na universidade publica que sofre algum distúrbio de deficiencia auditiva, explicando a como se desenvolver e se aperfeiçoar',
    data: '19/04/24',
    hora: '16:00',
  },
]

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
const server = express()

const nunjucks = require('nunjucks')
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
  .listen(5500)
