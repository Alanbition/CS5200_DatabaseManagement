const mongoose= require('mongoose')

const studentModel = require('../models/student.model.server')
const answerModel = require('../models/answer.model.server')
const questionModel = require('../models/question.model.server')
const quiz_widgetModel = require('../models/quiz-widget.model.server')

function truncateDatabase() {
	return Promise.all([
		quiz_widgetModel.deleteMany({}).exec(),
		answerModel.deleteMany({}).exec(),
		questionModel.deleteMany({}).exec(),
		studentModel.deleteMany({}).exec()
		])
	};
createStudent = student => studentModel.create(student)
deleteStudent = studentId => studentModel.deleteOne({_id: studentId})
createQuestion = question => questionModel.create(question)
deleteQuestion = questionId => questionModel.deleteOne({_id: questionId})
answerQuestion = (studentId, questionId, answer) => {
	answer.student = studentId;
	answer.question = questionId;
	answerModel.create(answer);
};
deleteAnswer = answerId => answerModel.deleteOne({_id: answerId})

findAllStudents = () => studentModel.find()
findStudentById = studentId => studentModel.findById(studentId)
findAllQuestions = () => questionModel.find()
findQuestionById = questionId => questionModel.find({_id: questionId})
findAllAnswers = () => answerModel.find()
findAnswerById = answerId => answerModel.find({_id: answerId})
findAnswersByStudent = studentId => answerModel.find({student: studentId})
findAnswersByQuestion = questionId => answerModel.find({question: questionId})
updateStudent = (studentId, student) => studentModel.update({_id: studentId}, {$set: student})


function populateDatabase() {
	return Promise.all([
		createStudent({
			_id: 123,
			firstName: 'Alice',
			lastName: 'Wonderland',
			username: 'alice',
			password: 'alice',
			gradYear: '2020',
			scholarship: 15000
		}),
		createStudent({
			_id: 234,
			firstName: 'Bob',
			lastName: 'Hope',
			username: 'bob',
			password: 'bob',
			gradYear: '2021',
			scholarship: 12000
		}),
		createQuestion({
 			_id: 321,
 			question: 'Is the following schema valid?',
 			points: 10,
 			questionType: 'TRUE_FALSE',
 			isTrue: false
		}),
		createQuestion({
 			_id: 432,
 			question: 'DAO stands for Dynamic Access Object.',
 			points: 10,
 			questionType: 'TRUE_FALSE',
 			isTrue: false
		}),
		createQuestion({
 			_id: 543,
 			question: 'What does JPA stand for?',
 			points: 10,
 			questionType: 'MULTIPLE_CHOICE',
 			choices:['Java Persistence API','Java Persisted Application','JavaScript Persistence API','JSON Persistent Associations'],
 			correct:1
		}),
		createQuestion({
 			_id: 654,
 			question: 'What does ORM stand for?',
 			points: 10,
 			questionType: 'MULTIPLE_CHOICE',
 			choices:["Object Relational Model", "Object Relative Markup" ,"Object Reflexive Model", "Object Relational Mapping"],
 			correct:4
		}),
		answerQuestion(
			123,
			321,
			{
 				_id: 123,
 				trueFalseAnswer: true
		}),
		answerQuestion(
			123,
			432,
			{
 				_id: 234,
 				trueFalseAnswer: false
		}),
		answerQuestion(
			123,
			543,
			{
 				_id: 345,
 				multipleChoiceAnswer: 1
		}),
		answerQuestion(
			123,
			654,
			{
 				_id: 456,
 				multipleChoiceAnswer: 2
		}),
		answerQuestion(
			234,
			321,
			{
 				_id: 567,
 				trueFalseAnswer: false
		}),
		answerQuestion(
			234,
			432,
			{
 				_id: 678,
 				trueFalseAnswer: true
		}),
		answerQuestion(
			234,
			543,
			{
 				_id: 789,
 				multipleChoiceAnswer: 3
		}),
		answerQuestion(
			234,
			654,
			{
 				_id: 890,
 				multipleChoiceAnswer: 4
		})

	]);
}



module.exports = {
 truncateDatabase,populateDatabase,
 createQuestion,deleteQuestion,
 answerQuestion,deleteAnswer,
 findAllQuestions,findQuestionById,
 findAllAnswers,findAnswerById,
 findAnswersByStudent,findAnswersByQuestion,
 createStudent, findAllStudents, 
 findStudentById, updateStudent, 
 deleteStudent }
