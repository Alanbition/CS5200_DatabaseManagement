const mongoose = require('mongoose')
const student = require('./student.schema.server')
const question = require('./question.schema.server')
const answerSchema = mongoose.Schema({
 _id: Number,
 trueFalseAnswer: Boolean,
 multipleChoiceAnswer: Number,
 student: Number,
 question: Number
}, {collection: 'answers'})

module.exports = answerSchema;