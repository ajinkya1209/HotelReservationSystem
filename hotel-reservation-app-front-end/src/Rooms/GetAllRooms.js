//import React from 'react';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import RoomCard from './RoomCard';
import { useParams, useNavigate } from 'react-router-dom';
import { Container, Card, Row, Col, Button } from 'react-bootstrap';
import '../Hotels/HotelCard.css';
const getRandomRoomImage = () => {
    const roomId = Math.floor(Math.random() * 1000); // Generate a random room ID
    return `https://source.unsplash.com/400x300/?hotel,deluxe-rooms,luxury-rooms/${roomId}`;
};

const GetAllRooms = () => {
    const [roomData, setRoomData] = useState([]);
    const { hotelId } = useParams();


    useEffect(() => {
        axios.get(`http://localhost:8080/room/get-room`)
            .then(response => {
                setRoomData(response.data);
                console.log("room list ");
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, [hotelId]);
    // const hotelName = roomData[0].hotel.hotelName; // Accessing the hotelName from the first object
    console.log();

    return (


            <div className='card-container'>
                {roomData.map(item => <RoomCard data={item} myurl={getRandomRoomImage(item.roomId)}></RoomCard>)}
            </div>
        
    );
}

export default GetAllRooms;