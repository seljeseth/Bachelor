import React from 'react';
import Button from '@material-ui/core/Button';
import Box from "@material-ui/core/Box";



const boxProps = {
	width: 600,
	margin: 1,
};


export default function ButtonBox(text, func)
{
	let bool = text === 'Check Server Status';
	return (
		<Box {...boxProps}>
			<Button variant = {bool ? 'outlined' : 'contained'}
			        color = {bool ? "primary" : "secondary"}
			        onClick = {func}
			        fullWidth>
				{text}
			</Button>
		</Box>
	);
}