events = []

class EventController {
    getEvents(request, response) {
        return response.json(events)
    }

    createEvent(request, response) {
        const { titulo, img, nome, categoria, sobre, data, horaIni, horaFim } = request.body;
        const newEvent = {
            titulo,
            img,
            nome,
            categoria,
            sobre,
            data,
            horaIni,
            horaFim
        }

        events.push(newEvent);

        // return response.json(newEvent)
    }
}

const eventController = new EventController();

module.exports = {
    eventController
}
