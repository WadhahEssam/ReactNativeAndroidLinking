'use strict';
import { useEffect, useState } from 'react';
import {
  NativeEventEmitter,
  requireNativeComponent,
  ViewStyle,
} from 'react-native';

var ProgressBar = requireNativeComponent('CustomProgressBar');

var generateRandomColor = () => Math.floor(Math.random()*16777215).toString(16);


const ProgressBarWrapper = ({
  progress,
  color,
  style,
}: {
  progress: number;
  color: string;
  style: ViewStyle;
}) => {
  const [actualColor, setColor] = useState(color);

  console.log({actualColor})

  useEffect(() => {
    const eventEmitter = new NativeEventEmitter();
    let eventListener = eventEmitter.addListener('ChangeColorEvent', event => {
      console.log('here');
      setColor(`#${generateRandomColor()}`);
    });

    // Removes the listener once unmounted
    return () => {
      eventListener.remove();
    };
  }, []);

  return <ProgressBar progress={progress} color={actualColor} style={style} />;
};

export default ProgressBarWrapper;