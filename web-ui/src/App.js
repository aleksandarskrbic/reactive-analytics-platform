import React, { useState, useRef, useEffect, createRef } from 'react';
import { TextField, Button, Checkbox } from '@material-ui/core';
import axios from 'axios';
import ReactJson from 'react-json-view'

function App() {
  const locationRef = useRef(1)
  const laptopRef = useRef(false)
  const phoneRef = useRef(false)
  const [report, setReport] = useState({})
  const [full, setFull] = useState(false)

  useEffect(() => {
    console.log(report.location)
  }, [report])

  function handleLaptop(e) {
    const value = laptopRef.current.value
    laptopRef.current.value = !value
  }

  function handlePhone(e) {
    const value = phoneRef.current.value
    phoneRef.current.value = !value
  }

  function getReport(e) {
    let location = locationRef.current.value
    let laptop = laptopRef.current.value
    let phone = phoneRef.current.value 
    let url = ''

    if (!laptop && !phone) url = 'http://localhost:9003/api/query/' + location + "/full"
    else {
      if (laptop && phone) url = 'http://localhost:9003/api/query/' + location + "/full"
      else {
        if (laptop) url = 'http://localhost:9003/api/query/' + location + "/laptop"
        else url = 'http://localhost:9003/api/query/' + location + "/phone"
      }
    }

    if (url.includes("full")) setFull(true)
    axios.get(url).then(res => setReport(res.data))
  }

  return (
      <>
      <TextField inputRef={locationRef} label="Enter Location ID" variant="outlined" inputProps={{min: 0, style: { textAlign: 'center' }}} />
      <br/>
      <br/>
      <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
        <label>
          Laptop <Checkbox ref={laptopRef} onChange={handleLaptop} inputProps={{ 'aria-label': 'primary checkbox' }}/>
        </label>
        <label>
          Phone  <Checkbox ref={phoneRef} onChange={handlePhone} inputProps={{ 'aria-label': 'primary checkbox' }}/>
        </label>
      </div>
      <br/>
      <div style={{display: 'flex',  justifyContent:'center', alignItems:'center'}}>
        <Button variant="contained" color="primary" onClick={getReport}>
          Get Report
        </Button>
      </div>
      <br/>
      <br/>
      <div>
        <ReactJson src={report} />
      </div>
      </>
  )
}

export default App;