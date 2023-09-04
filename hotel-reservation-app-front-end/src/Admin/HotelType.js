import React from 'react';

const HotelType = ({ name, value, onChange }) => {
  return (
    <div>
      <label htmlFor={name}>Hotel Type</label>
      <select
        id={name}
        name={name}
        value={value}
        onChange={onChange}
      >
        <option value="TWOSTAR">Two Star</option>
        <option value="THREESTAR">Three Star</option>
        <option value="FOURSTAR">Four Star</option>
        <option value="FIVESTAR">Five Star</option>
      </select>
    </div>
  );
};

export default HotelType;
