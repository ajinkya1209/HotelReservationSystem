import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { Table, Button } from 'react-bootstrap';
import { useParams, Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom';

import { useLocation } from 'react-router-dom/cjs/react-router-dom.min';

const buttonSpace = {
  marginRight: '10px',
  marginLeft: '6px',
  padding: '5px 10px',
  borderRadius: '5px',
  fontSize: '17px',
  fontWeight: 'bold',
  boxShadow: '2px 2px 4px rgba(0, 0, 0, 0.2)',
};

const divStyles = {
  padding: '10px',
  backgroundColor: 'lavender',
  border: '5px solid #ddd',
  height: '75px',
};

const headers = {
  Authorization: localStorage.getItem('token'),
};

const GetRooms = () => {
  //const { hotelId } = useParams();
  const location=useLocation();
  const {hotelData}=location.state;
  const {hotelId,setHotelId}=useState(hotelData);
  console.log("hotelData in getRooms");
  console.log(hotelData);
  console.log("hotelId in getRooms");
  console.log(hotelData.hotelId);
  const [rooms, setRooms] = useState([]);
  const history = useHistory();
  // const location = useLocation(); // Initialize useLocation hook
  // const { hotelData } = location.state; 
  useEffect(() => {
    
    axios
      .get(`http://localhost:8080/room/hotels/${hotelData.hotelId}`,{ headers })
      .then((response) => {
          setRooms(response.data);
      })
      .catch((error) => {
        console.error('Error fetching room data:', error);
      });
  }, [hotelId]);

  const handleDeleteRoom = (roomId) => {
    // Navigate to DeleteParticularRoom component with roomId as a parameter
    console.log('in getRooms roomId'+roomId);
    const isConfirmed = window.confirm("Are you sure?");
    if (!isConfirmed)
    return;
    console.log(hotelId+"hotel Id in getRooms");
    history.push(`/delete-room/${roomId}`,{roomId:roomId,hotelId:hotelData.hotelId});
    //console.log("in handleDeleteRoom");
  };
  const redirectBack=()=>{
    history.push('/hotel-list-admin');
  }

  return (
    <div style={{ margin: '0 25px', border: '3px solid #ccc', padding: '25px' }}>
      <div style={divStyles}>
        <Link to={{ pathname: `/add-hotel` }}>
          <Button variant="primary">Add New Hotel</Button>
        </Link>
      </div>
      <div><button onClick={redirectBack}>Go Back</button></div>
      <h2>Rooms for Hotel ID: {hotelData.hotelId}</h2>
      <h2>Hotel Name: {hotelData.hotelName}</h2>
            <Table striped bordered hover>
        <thead>
          <tr>
            <th><b>Room ID</b></th>
            <th><b>Room Type</b></th>
            <th><b>About Room</b></th>
            <th><b>Room Facilities</b></th>
            <th><b>Actions</b></th>
          </tr>
        </thead>
        <tbody>
          {rooms.map((room) => (
            <tr key={room.id}>
              <td>{room.id}</td>
              <td>{room.roomType}</td>
              <td>{room.aboutRoom}</td>
              <td>{room.roomFacilities}</td>
              <td>
                <Button
                  style={buttonSpace}
                  variant="danger"
                  onClick={() => handleDeleteRoom(room.id)}
                >
                  Delete Room
                </Button>
                {/* <Link to=''>
                <Button style={buttonSpace} variant="danger">  Delete Room </Button>
                </Link> */}
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
};

export default GetRooms;
