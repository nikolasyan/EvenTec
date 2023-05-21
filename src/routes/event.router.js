const { Router } = require('express');
const { eventController } = require('../controllers/event.controller');

const eventRouter = Router();

eventRouter.get('/events/all', eventController.getEvents);
eventRouter.post('/cadastrar', eventController.createEvent);

module.exports = {
    eventRouter,
};
