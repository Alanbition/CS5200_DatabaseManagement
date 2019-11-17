require('./db')();
const dao = require('./daos/university.dao.server');
const assert = require('assert');


dao.truncateDatabase()
	.then(() =>
		//console.log('Testing TruncateDatabase');
		dao.populateDatabase()
	)
	.then(() =>
		//console.log('Testing testStudentsInitialCount')
		testStudentsInitialCount()
	)
	.then(() =>
		//console.log('Testing testQuestionsInitialCount')
		testQuestionsInitialCount()
	)
	.then(() =>
		//console.log('Testing testAnswersInitialCount')
		testAnswersInitialCount()
	)
	.then(() =>
		//console.log('Testing testDeleteAnswer')
		testDeleteAnswer()
	)
	.then(() =>
		//console.log('Testing testDeleteQuestion')
		testDeleteQuestion()
	)
	.then(() =>
		//console.log('Testing testDeleteStudent')
		testDeleteStudent()
	);

const testStudentsInitialCount = () => {
	return dao.findAllStudents().then(reply => assert.equal(reply.length, 2))
};

const testQuestionsInitialCount = () => {
	return dao.findAllQuestions().then(reply => assert.equal(reply.length, 4))
};

const testAnswersInitialCount = () => {
	return dao.findAllAnswers().then(reply => assert.equal(reply.length, 8))
};

/*
const testDeleteAnswer = () => {
	return dao.deleteAnswer(890).then(() => { dao.findAllAnswers()
		.then(reply => assert.equal(reply.length, 7));
		dao.findAnswersByStudent(234)
		.then(reply => assert.equal(reply.length, 3));
	});
};

const testDeleteQuestion = () => {
	return dao.deleteQuestion(321).then(() => dao.findAllQuestions().then(reply => assert.equal(reply.length, 3)))
};

const testDeleteStudent = () => {
	return dao.deleteStudent(234).then(() => dao.findAllStudents().then(reply => assert.equal(reply.length, 1)))
};
*/


