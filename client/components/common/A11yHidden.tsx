import React, { ReactElement } from 'react';

interface A11yHiddenProps {
  as?: string;
  children: string | ReactElement;
  [key: string]: any;
}

export default function A11yHidden({
  as = 'span',
  ...restProps
}: A11yHiddenProps): ReactElement {
  return React.createElement(as, {
    className: 'a11yHidden',
    ...restProps,
  });
}
