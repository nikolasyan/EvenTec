events = [
    {
        titulo: "teste1",
        img: "https://cdn3d.iconscout.com/3d/premium/thumb/male-customer-call-service-6760893-5600700.png?f=webp",
        nome: "Evento 01",
        categoria: "Marketing",
        sobre: "Exemplo de evento sobre marketing",
        data: "12/15/68",
        horaIni: "20:54",
        horaFim: "23:00"
      },
      {
        titulo: "teste1",
        img: "https://static.vecteezy.com/system/resources/thumbnails/008/933/208/small/3d-render-male-avatar-with-blue-sweater-good-for-profile-picture-photo.jpg",
        nome: "Evento 02",
        categoria: "Administração",
        sobre: "Outro exemplo de evento porém sobre administração",
        data: "12/58/41",
        hora: "19:56",
        horaFim: "23:00"
      }
]

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
