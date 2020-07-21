import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';

ReactDOM.render(
  <React.StrictMode>
      <div
    style={{
        position: 'absolute', left: '50%', top: '30%',
        transform: 'translate(-50%, -50%)'
    }}
    >
    <App />
    </div>
  </React.StrictMode>,
  document.getElementById('root')
);

