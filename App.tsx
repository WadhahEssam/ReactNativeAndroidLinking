/* eslint-disable react-native/no-inline-styles */
/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React, { useState } from 'react';
import type { PropsWithChildren } from 'react';
import {
  ActivityIndicator,
  Button,
  Dimensions,
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  TextInput,
  useColorScheme,
  View,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
} from 'react-native/Libraries/NewAppScreen';
import ProgressBar from './nativeModules/ProgressBar';
import { ShadowedView } from 'react-native-fast-shadow';
import ImageNativeView from './nativeModules/ImageNativeView';
import CustomModule from './nativeModules/CustomModule';
import CustomView from './nativeModules/CustomView';

type SectionProps = PropsWithChildren<{
  title: string;
}>;

function Section({ children, title }: SectionProps): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark'; return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
}

function App(): JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';
  const [text, setText] = useState('test');

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <View>
            <ProgressBar
              progress={20}
              color={'green'}
              style={{
                height: 50,
                margin: 20,
                width: Dimensions.get('window').width - 2 * 20,
              }}
            />

            <View style={{
              flexDirection: 'row',
              alignItems: 'center',
              justifyContent: 'center'
            }}>
              {/* Setting a height and width is so important */}
              <CustomView style={{
                height: 500,
                width: 350,
                marginBottom: 40
              }}
              />
            </View>

            <ShadowedView style={{
              height: 100,
              width: 100,
              marginStart: 100,
              backgroundColor: 'white',
              shadowOffset: {
                height: -2,
                width: 0,
              },
              shadowRadius: 20,
              shadowOpacity: 0.07,
              cornerRadii: {
                topLeft: 100
              }
            }}
            />

            <View style={{ width: 200 }}>
              <TextInput value={text} onChangeText={(t) => {
                setText(t);
              }}
                placeholder="write message"
              />
              <Button title='Call CustomModule (Open New Activity)' onPress={() => {
                if (text) {
                  CustomModule.launchAnotherActivity(text);
                }
              }} />
            </View>

            <ActivityIndicator size={240} />

            <ImageNativeView
              style={{ height: 100, width: 300 }}
              // source={
              //   'https://miro.medium.com/v2/resize:fit:1400/format:webp/1*5fce6uH9gbqf5_YO4xRW9Q.png'
              // }
              sources={[
                'https://miro.medium.com/v2/resize:fit:1400/format:webp/1*5fce6uH9gbqf5_YO4xRW9Q.png'
              ]}
            />
          </View>
          {/* <Section title="Step One">
            Edit <Text style={styles.highlight}>App.tsx</Text> to change this
            screen and then come back to see your edits.
          </Section>
          <Section title="See Your Changes"> */}
          {/* <ReloadInstructions />
          </Section> */}
          <Section title="Debug">
            <DebugInstructions />
          </Section>
          <Section title="Learn More">
            Read the docs to discover what to do next:
          </Section>
          <LearnMoreLinks />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
