import React from 'react';
function Card({ inputId, checked, children }) {
    return (
        <label htmlFor={inputId} className={checked ? 'active' : ''}>
            {children}
        </label>
    );
}

export default Card;