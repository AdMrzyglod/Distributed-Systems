
import React, { useState } from 'react';
import {Survey ,Answer} from "../gen/survey_pb";
import client from "../client/client";

export default function SurveyCreate(){


    const [question, setQuestion] = useState('');
    const [answer,setAnswer] = useState('');
    const [answers,setAnswers] = useState([]);
    const [username,setUsername] = useState('');

    const handleAddAnswer = (e) => {
        e.preventDefault()

        if(answer.trim().length>0){
            setAnswers([...answers,answer.trim()])
            setAnswer('')

        }
    }

    const handleClear = (e) => {
        e.preventDefault()

        setQuestion('')
        setAnswer('')
        setAnswers([])
        setUsername('')
    }

    const handleSubmit = (e) => {
        e.preventDefault()

        if(question.trim().length>5 && answers.length>1 && username.trim().length>3){
            const answerList = answers.map((data,index)=> new Answer().setAnswerId(index).setAnswer(data));
            const request = new Survey();
            request
                .setSurveyid(-1)
                .setQuestion(question)
                .setAnswersList(answerList)
                .setUsername(username)
                .setVotesList([])

            client.createSurvey(request, {}, (err, response) => {
                console.log(response);
            });
            setQuestion('')
            setAnswer('')
            setAnswers([])
            setUsername('')
        }
    }

    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center",justifyContent: "flex-start", padding: "20px", width: "400px"}}>
            <form style={{display: "flex", flexDirection: "column"}} onSubmit={handleSubmit}>
                <h2>Dodaj kurs</h2>
                <div style={{display: "flex", flexDirection: "column", padding: "20px", width: "400px"}}>
                    <label>Użytkownik:</label>
                    <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} required/>
                </div>
                <div style={{display: "flex", flexDirection: "column", padding: "20px", width: "400px"}}>
                    <label>Pytanie:</label>
                    <input type="text" value={question} onChange={(e) => setQuestion(e.target.value)} required/>
                </div>
                <div style={{display: "flex", flexDirection: "column", padding: "20px", width: "400px"}}>
                    <label>Odpowiedzi:</label>
                    {
                        answers.map((data,index) => {
                            return <p key={index+data}>{data}</p>;
                        })
                    }
                    <input type="text" value={answer} onChange={(e) => setAnswer(e.target.value)}/>
                    <button onClick={handleAddAnswer}>Dodaj odpowiedź</button>
                </div>
                <button type="submit">Dodaj</button>
                <br/>
                <button type="button" onClick={handleClear}>Wyczyść</button>
            </form>
        </div>
    )
}
