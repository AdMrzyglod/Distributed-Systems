import React,{ useEffect,useState } from 'react';
import client from "../client/client";
const {Answer,Survey,Vote, ListData,SurveyData,AnswerStats,SurveyStats} = require('../gen/survey_pb');

export default function SurveyDetails({data}){

    const [showVotes,setShowVotes] = useState(false);
    const [showStats,setShowStats] = useState(false);
    const [stats,setStats] = useState(new SurveyStats())
    const [votes, setVotes] = useState([]);
    const [answerID,setAnswerID] = useState(0);
    const [username,setUsername] = useState('');

    const handleShowVotes = (e) =>{
        e.preventDefault()

        if(!showVotes){
            let surveyData = new SurveyData();
            surveyData.setSurveyid(data.getSurveyid());
            let listVotes = []

            let voteStream = client.getSurveyVotes(surveyData, {})
            voteStream.on('data', (response) => {
                listVotes.push(response)
                setVotes([...listVotes])
                console.log(listVotes)
            });
            voteStream.on('error', (err) => {
                console.log(err);
            });
        }
        else{
            setVotes([])
        }
        setShowVotes(!showVotes);
    }

    const handleVote = (e) => {
        e.preventDefault()

        if(answerID>=0 && answerID<data.getAnswersList().length && username.trim().length>3){
            const request = new Vote();
            request
                .setSurveyid(data.getSurveyid())
                .setAnswerid(answerID)
                .setUsername(username)

            client.surveyVote(request, {}, (err, response) => {
                console.log(response);
            });
            setAnswerID(0)
            setUsername('')
        }
    }

    const handleShowStats = (e) => {
        e.preventDefault()

        if(!showStats){
            let surveyData = new SurveyData();
            surveyData.setSurveyid(data.getSurveyid());

            client.getSurveyStats(surveyData, {}, (err, response) => {
                setStats(response)
                console.log(response);
            });
        }
        else{
            setStats(new SurveyStats())
        }
        setShowStats(!showStats);
    }

    return(
        <div style={{display: "flex", flexDirection: "column", border: "2px solid black", padding: "20px", width: "400px"}}>
            <div style={{display: "flex", flexDirection: "column"}}>
                <h3>Ankieta ID: {data.getSurveyid()}</h3>
                <ul>
                    <li>Użytkownik: {data.getUsername()}</li>
                    <li>Pytanie: {data.getQuestion()}</li>
                    {data.getAnswersList().map((answer) => {
                        return <li>{answer.getAnswerId() + "  " + answer.getAnswer()}</li>
                    })}
                </ul>
            </div>
            <div style={{display: "flex", flexDirection: "column"}}>
                <h2>Głosowanie</h2>
                <div style={{display: "flex", flexDirection: "column"}}>
                    <label>Użytkownik:</label>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required/>
                </div>
                <div style={{display: "flex", flexDirection: "column"}}>
                    <label>Odpowiedź numer:</label>
                    <input type="number" value={answerID} onChange={(e) => setAnswerID(e.target.value)} required/>
                </div>
                <button onClick={handleVote}>Zagłosuj</button>
            </div>
            <button onClick={handleShowStats}>Pokaż statystyki</button>
            {
                showStats ?
                    <div style={{display: "flex", flexDirection: "column"}}>
                        <h3>Statystyki</h3>
                        <ul>
                            <li>Pytanie: {stats.getQuestion()}</li>
                            <li>Liczba głosów: {stats.getVotesnumber()}</li>
                            {stats.getAnswerstatsList().map((answer) => {
                                return <li>{answer.getAnswer().getAnswerId() + "  " + answer.getAnswer().getAnswer()+ " : Liczba głosów: " + answer.getNumberofvotes() + " ,Procent głosów: " + answer.getPercentvotes().toFixed(2)}</li>
                            })}
                        </ul>
                    </div>
                    :
                    <></>
            }
            <button onClick={handleShowVotes}>Pokaż głosy</button>
            {
                showVotes ?
                    <div style={{display: "flex", flexDirection: "column"}}>
                        <ul>
                            {votes.map((vote, index) => {
                                let list = data.getAnswersList().filter((element) => element.getAnswerId() === vote.getAnswerid());
                                return <li key={index}>{"User: " + vote.getUsername() + " , Answer: "+list[0].getAnswerId()+ " " + list[0].getAnswer()}</li>;
                            })}
                        </ul>
                    </div>
                    :
                    <></>
            }
        </div>
    )
}