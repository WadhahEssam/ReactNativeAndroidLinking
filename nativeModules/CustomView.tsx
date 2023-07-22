import {ViewStyle, requireNativeComponent} from 'react-native';

const CustomViewNative = requireNativeComponent('CustomView');

const CustomView = ({style}: {style: ViewStyle}) => {
  return <CustomViewNative style={{
    width: 100,
    height: 100,
    ...style
  }} />;
};

export default CustomView;
