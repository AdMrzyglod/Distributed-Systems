package org.grpc;

import io.grpc.stub.StreamObserver;
import org.grpc.gen.*;
import org.grpc.gen.SurveyServiceGrpc.SurveyServiceImplBase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class SurveyServiceI extends SurveyServiceImplBase {

    private Map<Integer,Survey> surveyMap = new HashMap<>();

    public SurveyServiceI() {
        List<Survey> surveyList = new ArrayList<>();
        surveyList.add(Survey.newBuilder().setSurveyID(0).setQuestion("Wybierz opcje").setUsername("Nickname")
                .addAnswers(Answer.newBuilder().setAnswerId(0).setAnswer("Opcja 1"))
                .addAnswers(Answer.newBuilder().setAnswerId(1).setAnswer("Opcja 2"))
                .addAnswers(Answer.newBuilder().setAnswerId(2).setAnswer("Opcja 3"))
                .addAnswers(Answer.newBuilder().setAnswerId(3).setAnswer("Opcja 4"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(2).setUsername("Nickname1"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(3).setUsername("Nickname2"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(3).setUsername("Nickname3"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(0).setUsername("Nickname4"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(0).setUsername("Nickname5"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(1).setUsername("Nickname6"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(2).setUsername("Nickname7"))
                .addVotes(Vote.newBuilder().setSurveyID(0).setAnswerID(2).setUsername("Nickname8"))
                .build());
        surveyList.add(Survey.newBuilder().setSurveyID(1).setQuestion("Wybierz inna opcje").setUsername("Nickname00")
                .addAnswers(Answer.newBuilder().setAnswerId(0).setAnswer("Opcja 11"))
                .addAnswers(Answer.newBuilder().setAnswerId(1).setAnswer("Opcja 22"))
                .addAnswers(Answer.newBuilder().setAnswerId(2).setAnswer("Opcja 33"))
                .addAnswers(Answer.newBuilder().setAnswerId(3).setAnswer("Opcja 44"))
                .addAnswers(Answer.newBuilder().setAnswerId(4).setAnswer("Opcja 55"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(0).setUsername("Nickname11"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(1).setUsername("Nickname22"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(2).setUsername("Nickname33"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(4).setUsername("Nickname44"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(4).setUsername("Nickname55"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(3).setUsername("Nickname66"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(2).setUsername("Nickname77"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(2).setUsername("Nickname88"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(0).setUsername("Nickname99"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(0).setUsername("Nickname100"))
                .addVotes(Vote.newBuilder().setSurveyID(1).setAnswerID(1).setUsername("Nickname101"))
                .build());
        surveyList.add(Survey.newBuilder().setSurveyID(2).setQuestion("Wybierz odpowiedz").setUsername("Nickname000")
                .addAnswers(Answer.newBuilder().setAnswerId(0).setAnswer("Opcja 111"))
                .addAnswers(Answer.newBuilder().setAnswerId(1).setAnswer("Opcja 222"))
                .addAnswers(Answer.newBuilder().setAnswerId(2).setAnswer("Opcja 333"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(0).setUsername("Nickname111"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(1).setUsername("Nickname222"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(2).setUsername("Nickname333"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(2).setUsername("Nickname444"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(1).setUsername("Nickname555"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(1).setUsername("Nickname666"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(0).setUsername("Nickname777"))
                .addVotes(Vote.newBuilder().setSurveyID(2).setAnswerID(1).setUsername("Nickname888"))
                .build());
        surveyList.add(Survey.newBuilder().setSurveyID(3).setQuestion("Wybierz inna odpowiedz").setUsername("NicknameA")
                .addAnswers(Answer.newBuilder().setAnswerId(0).setAnswer("Opcja A"))
                .addAnswers(Answer.newBuilder().setAnswerId(1).setAnswer("Opcja B"))
                .addAnswers(Answer.newBuilder().setAnswerId(2).setAnswer("Opcja C"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(0).setUsername("NicknameA"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(2).setUsername("NicknameB"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(2).setUsername("NicknameC"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(2).setUsername("NicknameD"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(2).setUsername("NicknameE"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(1).setUsername("NicknameF"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(1).setUsername("NicknameG"))
                .addVotes(Vote.newBuilder().setSurveyID(3).setAnswerID(1).setUsername("NicknameH"))
                .build());
        surveyList.forEach((survey -> {
            this.surveyMap.put(survey.getSurveyID(),survey);
        }));

    }

    @Override
    public void createSurvey(Survey request, StreamObserver<Survey> responseObserver) {
        System.out.println(">>> createSurvey");
        Integer surveyID = request.getSurveyID();
        if(surveyMap.containsKey(surveyID)){
            System.out.println(">>> Survey already exists.");
            Survey result = Survey.newBuilder(surveyMap.get(surveyID)).build();
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        }
        else{
            int key = 0;
            if(this.surveyMap.size()>0){
                key = Collections.max(this.surveyMap.keySet())+1;
            }
            Survey result = Survey.newBuilder(request).setSurveyID(key).build();
            this.surveyMap.put(key,result);
            System.out.println(">>> Survey created.");
            System.out.println(result);
            responseObserver.onNext(result);
            responseObserver.onCompleted();
        }
    }

    @Override
    public void listSurveys(ListData request, StreamObserver<Survey> responseObserver) {
        System.out.println(">>> listSurveys");
        System.out.println(">>> Number of surveys: "+surveyMap.size()+" Max len: "+request.getMaxLen());
        int i=0;
        for(Survey survey: surveyMap.values()){
            if(i>=request.getMaxLen()){
                break;
            }
            Survey response = Survey.newBuilder(survey).build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
        responseObserver.onCompleted();
    }

    @Override
    public void surveyVote(Vote request, StreamObserver<Vote> responseObserver) {
        System.out.println(">>> surveyVote");
        Integer surveyID = request.getSurveyID();
        if(surveyMap.containsKey(surveyID)){
            Vote resultVote = Vote.newBuilder(request).build();
            Survey survey = surveyMap.get(surveyID);
            if(survey.getVotesList().stream().filter(data->data.getUsername().equals(request.getUsername())).toList().size()>0){
                System.out.println(">>> User: "+request.getUsername()+" already voted.");
                responseObserver.onNext(Vote.newBuilder().build());
                responseObserver.onCompleted();
            }
            else{
                Survey.Builder surveyBuilder = survey.toBuilder().addVotes(resultVote);
                surveyMap.put(surveyID, surveyBuilder.build());
                System.out.println(">>> Vote added.");
                responseObserver.onNext(resultVote);
                responseObserver.onCompleted();
            }
        }
        else{
            System.out.println(">>> Survey not exists.");
            responseObserver.onNext(Vote.newBuilder().build());
            responseObserver.onCompleted();
        }
    }

    @Override
    public void getSurveyVotes(SurveyData request, StreamObserver<Vote> responseObserver) {
        System.out.println(">>> getSurveyVotes");
        Integer surveyID = request.getSurveyID();
        System.out.println(">>> Number of votes: "+surveyMap.get(surveyID).getVotesCount());
        for(Vote vote: surveyMap.get(surveyID).getVotesList()) {
            Vote response = Vote.newBuilder(vote).build();
            responseObserver.onNext(response);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseObserver.onCompleted();
    }

    @Override
    public void getSurveyStats(SurveyData request, StreamObserver<SurveyStats> responseObserver) {
        System.out.println(">>> getSurveyStats");
        Integer surveyID = request.getSurveyID();
        if(!surveyMap.containsKey(surveyID)){
            System.out.println(">>> Survey not exists.");
            responseObserver.onNext(SurveyStats.newBuilder().build());
            responseObserver.onCompleted();
        }
        else{
            Survey survey = surveyMap.get(surveyID);
            Integer votesNumber = Math.max(1,survey.getVotesCount());
            List<AnswerStats> answerStats = new ArrayList<>();
            Map<Integer, Long> answerCount = survey.getVotesList().stream()
                    .collect(Collectors.groupingBy(Vote::getAnswerID, Collectors.counting()));
            for(Answer answer: survey.getAnswersList()){
                Long numberOfVotes = 0L;
                if(answerCount.containsKey(answer.getAnswerId())){
                    numberOfVotes = answerCount.get(answer.getAnswerId());
                }
                float percentVotes = new BigDecimal(((float)numberOfVotes/votesNumber)*100).setScale(2, RoundingMode.HALF_UP).floatValue();
                answerStats.add(AnswerStats.newBuilder().setAnswer(Answer.newBuilder(answer).build()).setNumberOfVotes(numberOfVotes).setPercentVotes(percentVotes).build());
            }
            SurveyStats.Builder surveyStatsBuilder = SurveyStats.newBuilder().setQuestion(survey.getQuestion()).setVotesNumber(votesNumber);

            for (AnswerStats stats : answerStats) {
                surveyStatsBuilder.addAnswerStats(stats);
            }

            SurveyStats surveyStats = surveyStatsBuilder.build();
            System.out.println(">>> Stats SurveyID: "+surveyID);
            responseObserver.onNext(surveyStats);
            responseObserver.onCompleted();
        }
    }
}
