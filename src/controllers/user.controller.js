users = []

class UserController {
    getUsers(request, response) {
        return response.json(users)
    }

    createUsers(request, response) {
        const { nome, email, senha } = request.body;
        const newUser = {
            nome,
            email,
            senha
        }

        users.push(newUser);
        //  return response.json(newUser)
        response.redirect('/')
    }
    
    
    login(request, response) {
        const { email, senha } = request.body;

        const user = users.find((user) => user.email === email && user.senha === senha);

        if (!user) {
            response.redirect('login')
        }

        response.redirect('/')
    }
    

}

const userController = new UserController();

module.exports = {
    userController
}
