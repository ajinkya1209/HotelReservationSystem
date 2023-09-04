//import React from 'react';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import RoomCard from './RoomCard';
import { useParams,useNavigate } from 'react-router-dom';
import {Container ,Card,Row, Col, Button} from 'react-bootstrap';  
import '../Hotels/HotelCard.css';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

const getRandomRoomImage = () => {
  const roomId = Math.floor(Math.random() * 1000); // Generate a random room ID
  return `https://source.unsplash.com/400x300/?hotel,deluxe-rooms,luxury-rooms/${roomId}`;
};

const Rooms = () => {
  const [roomData, setRoomData] = useState([]);
  const {hotelId} = useParams();
 const [flag,setFlag]=useState(true);
const history=useHistory();

  useEffect(() => {
    axios.get(`http://localhost:8080/room/get-empty-rooms/${hotelId}`)
      .then(response => {
        setRoomData(response.data);
        console.log("room list ");
        if(response.data.length==0)
        {
          //flag=false;
          setFlag(false);
        }
      })
      .catch(error => {
        console.error('Error fetching data:', error);
      });
  }, [hotelId]);
 // const hotelName = roomData[0].hotel.hotelName; // Accessing the hotelName from the first object
 console.log();
 
  return (
    <>
    {flag ?   
      <div className='card-container'>
      {roomData.map(item => <RoomCard className='' data={item} myurl={getRandomRoomImage(item.roomId)}></RoomCard> )}
      </div> 
      :(
        history.push('/data-not-found') // Navigating to another route when flag is false
      )
    }
    </>
    
  );
}

export default Rooms;