
const {Answer,Survey,Vote, ListData,SurveyData,AnswerStats,SurveyStats} = require('../gen/survey_pb');
const {SurveyServiceClient} = require('../gen/survey_grpc_web_pb');

const client = new SurveyServiceClient('http://127.0.0.1:8080');





export default client;