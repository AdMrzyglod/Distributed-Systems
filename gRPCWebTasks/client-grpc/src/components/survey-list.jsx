import React,{ useEffect,useState } from 'react';
import client from "../client/client";
import {ListData,Survey} from "../gen/survey_pb";
import SurveyCreate from "./survey-create";
import SurveyDetails from "./survey-details";

export default function SurveyList(){

    const [listItems, setListItems] = useState([]);
    const [showSurveys,setShowSurveys] = useState(false);



    const handleRefreshSurvey = (e) =>{
        e.preventDefault()

        if(!showSurveys){
            let listDataStream = new ListData();
            listDataStream.setMaxLen(20);
            let listSurvey = []
            let surveyStream = client.listSurveys(listDataStream, {})
            surveyStream.on('data', (response) => {
                listSurvey.push(response);
                setListItems([...listSurvey]);
                console.log(listSurvey);
            });
            surveyStream.on('error', (err) => {
                console.log(err);
            });
        }
        else{
            setListItems([])
        }
        setShowSurveys(!showSurveys)
    }



    return(
        <div style={{display: "flex", flexDirection: "column", alignItems: "center",justifyContent: "flex-start"}}>
            <div style={{display: "flex", flexDirection: "column", gap: "10px"}}>
                <SurveyCreate/>
            </div>
            <div style={{display: "flex", flexDirection: "column"}}>
                <h2>Ankiety {listItems.length}</h2>
                {
                    listItems.map((item)=><SurveyDetails key={item.getQuestion()} data={item}/>)
                }
            </div>
            <button onClick={handleRefreshSurvey}>{(!showSurveys ? "Pobierz ankiety" : "Ukryj ankiety")+"   "+listItems.length}</button>
        </div>
    )
}

