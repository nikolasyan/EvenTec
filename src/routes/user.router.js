const { Router } = require('express');
const { userController } = require('../controllers/user.controller');

const userRouter = Router();

userRouter.get('/users/all', userController.getUsers);
userRouter.post('/signup', userController.createUsers);
userRouter.post('/signin', userController.login);
module.exports = {
    userRouter,
};
