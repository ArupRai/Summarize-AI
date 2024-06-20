import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import App from './App';
import SummarizePage from './components/SummarizePage';
import HistoryPage from './components/HistoryPage';
import './index.css';

ReactDOM.render(
  <Router>
    <Routes>
      <Route path="/" element={<App />}>
        <Route path="summarize" element={<SummarizePage />} />
        <Route path="history" element={<HistoryPage />} />
      </Route>
    </Routes>
  </Router>,
  document.getElementById('root')
);
