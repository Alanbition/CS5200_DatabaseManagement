const mongoose = require('mongoose');
const studentSchema = mongoose.Schema({
   _id: Number,
   firstName: String,
   lastName: String,
   username: String,
   password: String,
   gradYear: String,
   scholarship: Number
}, {collection: 'students'});
module.exports = studentSchema;