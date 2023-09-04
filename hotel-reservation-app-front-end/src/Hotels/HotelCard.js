import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';
import './HotelCardSec.css';
import { Card, CardBody, CardFooter, Button, ButtonGroup, Stack, Divider, Heading, Text } from '@chakra-ui/react';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

const HotelCard = (props) => {
  const history=useHistory();
  // let flag=true;
  console.log(props.data.hotelId);
  // Object.keys(myObject).length === 0
  // if(Object.keys(props.data).length !== 0||props.data.length)
  // {
  //   flag=false;
  // }


  return (
    <>
     {/* {flag ? */}

    <div className="card-container">
      <Card maxW='sm' className="card-my">

        <div className="image-container">
          <img
            src={props.myurl}
            alt='Card'
            className="hotel-image"
          />
        </div>

        <CardBody className="card-content">
          <Stack spacing='3'>
            <Heading size='md'>{props.data.hotelName}</Heading>
            <Text>{props.data.hotelType}</Text>
            <Text color='blue.600' fontSize='2xl'>
              {props.data.contactNo}
            </Text>
            <Text>{props.data.hotelAmenities}</Text>
            <Text>{props.data.aboutHotel}</Text>
            <Text>{props.data.address.city}</Text>

          </Stack>
        </CardBody>
        <Divider />
        <CardFooter>
          <ButtonGroup spacing='2'>
            <Link to={`/userroom/${props.data.hotelId}`}>
              <Button variant='solid' colorScheme='blue' className="card-button">
                View Rooms
              </Button>
            </Link>
          </ButtonGroup>
        </CardFooter>
      </Card>



    </div>
    {/* :
    history.push('/data-not-found')} */}
    </>
  );
}

export default HotelCard;