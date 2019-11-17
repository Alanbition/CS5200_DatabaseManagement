const mongoose = require('mongoose')
const quiz_widgetSchema = require('./quiz-widget.schema.server')
module.exports = mongoose.model('Quiz_WidgetModel', quiz_widgetSchema)