import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import { useHistory } from 'react-router-dom';
import '../Hotels/HotelCardSec.css';
// import './RoomCard.css'; // Import custom CSS




const RoomCard = (props) => {
  const history = useHistory();
  const roomData = props.data;
  const myimgUrl = props.myurl;
  const bookingHandler = () => {
    const isAuthenticated = localStorage.getItem("authenticated") === "true";
    console.log(roomData);
    if (isAuthenticated) {
      history.push(`/booking`, { roomData: roomData }); // Navigate to booking page
    } else {
      history.push('/login'); // Navigate to signup page
    }
  };

  return (

    <>
      <div className="card-container">
        <Card className="card-my">
          <Card.Img variant="top" src={myimgUrl} />
          <Card.Body>
            <Card.Title>{props.data.roomType}</Card.Title>
            <Card.Text>
              {props.data.aboutRoom}
            </Card.Text>
            <Card.Text>
              {props.data.roomPrice}
            </Card.Text>
            <Button variant="primary" onClick={bookingHandler}>Book Now</Button>
          </Card.Body>
        </Card>
      </div>
    </>
  );
}

export default RoomCard;
