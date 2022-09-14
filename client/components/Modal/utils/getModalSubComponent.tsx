import { Children, isValidElement, ReactNode } from 'react';

/** utils */
const getModalSubComponent = (componentType: any, children: ReactNode) => {
  const childrenArray = Children.toArray(children);

  return childrenArray.filter(
    (child) => isValidElement(child) && child.type === componentType,
  );
};

export default getModalSubComponent;
